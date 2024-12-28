package id.aflah.universitieslist.data.mapper

import id.aflah.universitieslist.data.dbentity.UniversityDbEntity
import id.aflah.universitieslist.domain.entity.University
import javax.inject.Inject

class UniversityDbEntityMapper @Inject constructor() : Mapper<UniversityDbEntity, University>() {
    override fun fromResponseToEntity(from: UniversityDbEntity): University = with(from) {
        University(
            name = name.orEmpty(),
            country = country.orEmpty(),
            stateProvince = stateProvince.orEmpty(),
            alphaTwoCodes = alphaTwoCode.orEmpty(),
            domains = domains.orEmpty(),
            webPages = webPages.orEmpty(),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}