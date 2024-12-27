package id.aflah.universitieslist.data.repositoryimpl

import id.aflah.universitieslist.data.api.UniversityApiService
import id.aflah.universitieslist.data.mapper.UniversityMapper
import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.domain.repo.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val apiSource: UniversityApiService,
    private val universityMapper: UniversityMapper,
): UniversityRepository {
    override fun getUniversitiesList(country: String): Flow<ResultState<List<University>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiSource.getUniversities(country)
            val resultMapped = universityMapper.fromResponsesToEntities(response).toList()
            emit(ResultState.Success(resultMapped))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }
}