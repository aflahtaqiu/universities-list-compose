package id.aflah.universitieslist.data.mapper

import id.aflah.universitieslist.data.dbentity.UniversityDbEntity
import id.aflah.universitieslist.domain.entity.University
import javax.inject.Inject

class UniversityToDbEntityMapper @Inject constructor() : Mapper<University, UniversityDbEntity>() {
    override fun fromResponseToEntity(from: University): UniversityDbEntity = with(from) {
        UniversityDbEntity(
            name = name,
            country = country,
            stateProvince = stateProvince,
            alphaTwoCode = alphaTwoCodes,
            domains = domains,
            webPages = webPages,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}