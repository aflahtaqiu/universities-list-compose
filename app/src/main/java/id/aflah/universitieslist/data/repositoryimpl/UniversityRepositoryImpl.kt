package id.aflah.universitieslist.data.repositoryimpl

import id.aflah.universitieslist.data.api.UniversityApiService
import id.aflah.universitieslist.data.dao.UniversityDao
import id.aflah.universitieslist.data.mapper.UniversityDbEntityMapper
import id.aflah.universitieslist.data.mapper.UniversityMapper
import id.aflah.universitieslist.data.mapper.UniversityToDbEntityMapper
import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.domain.repo.UniversityRepository
import id.aflah.universitieslist.utils.DateHelper
import id.aflah.universitieslist.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val apiSource: UniversityApiService,
    private val universityDao: UniversityDao,
    private val universityMapper: UniversityMapper,
    private val universityDbEntityMapper: UniversityDbEntityMapper,
    private val universityToDbEntityMapper: UniversityToDbEntityMapper,
): UniversityRepository {

    override fun getUniversitiesList(country: String): Flow<ResultState<List<University>>> = flow {
        emit(ResultState.Loading)
        try {
            val localEntities = universityDao.loadUniversities()
            val isEntitiesFetchInDifferentDayExist = localEntities.any { universityData ->
                val updatedAtMillis = universityData.updatedAt.toLongOrNull().orZero()
                val todayMillis = DateHelper.getTodayMillis()
                todayMillis > updatedAtMillis
            } // toggle to fetch data if saved data in local DB in prev day
            if (localEntities.isEmpty() || isEntitiesFetchInDifferentDayExist) {
                val response = apiSource.getUniversities(country)
                val resultMappedDbEntity =
                    universityToDbEntityMapper.fromResponsesToEntities(universityMapper.fromResponsesToEntities(response).toList()).toList()
                        .map {
                            it.copy(updatedAt = DateHelper.getTodayMillis().toString())
                        }
                if (isEntitiesFetchInDifferentDayExist && localEntities.isNotEmpty()) {
                    universityDao.deleteAllUniversities()
                }
                universityDao.upsert(resultMappedDbEntity)
            }
            val newLocalEntities = universityDao.loadUniversities()
            val localResultMapped = universityDbEntityMapper.fromResponsesToEntities(newLocalEntities).toList()
            emit(ResultState.Success(localResultMapped))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }

    override fun searchUniversitiesByName(query: String): Flow<ResultState<List<University>>> = flow {
        emit(ResultState.Loading)
        try {
            val searchResultEntities = universityDao.searchUniversitiesByName(query)
            val searchResultMapped = universityDbEntityMapper.fromResponsesToEntities(searchResultEntities).toList()
            emit(ResultState.Success(searchResultMapped))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }
}