package id.aflah.universitieslist.domain.usecase

import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.domain.repo.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUniversitiesByNameUseCase @Inject constructor(
    private val repository: UniversityRepository,
): UseCase<Flow<ResultState<List<University>>>, String>() {
    override fun build(params: String?): Flow<ResultState<List<University>>> =
        repository.searchUniversitiesByName(
            query = params.orEmpty()
        )
}