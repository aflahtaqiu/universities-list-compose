package id.aflah.universitieslist.data.api

import id.aflah.universitieslist.data.constants.ApiConstants
import id.aflah.universitieslist.data.constants.EndpointConstants
import id.aflah.universitieslist.data.response.UniversityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApiService {
    @GET(EndpointConstants.SEARCH)
    suspend fun getUniversities(
        @Query(ApiConstants.COUNTRY) country: String = "",
    ): List<UniversityResponse>
}