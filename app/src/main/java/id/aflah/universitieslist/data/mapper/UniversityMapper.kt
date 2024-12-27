package id.aflah.universitieslist.data.mapper

import id.aflah.universitieslist.data.response.UniversityResponse
import id.aflah.universitieslist.domain.entity.University
import javax.inject.Inject

class UniversityMapper @Inject constructor() : Mapper<UniversityResponse, University>() {
    override fun fromResponseToEntity(from: UniversityResponse) = with(from) {
        University(
            name = name.orEmpty(),
            country = country.orEmpty(),
            stateProvince = stateProvince.orEmpty(),
            alphaTwoCodes = alphaTwoCode.orEmpty(),
            domains = domains.orEmpty(),
            webPages = webPages.orEmpty(),
        )
    }
}