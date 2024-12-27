package id.aflah.universitieslist.domain.repo

import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun getUniversitiesList(country: String): Flow<ResultState<List<University>>>
}