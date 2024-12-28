package id.aflah.universitieslist.data.mapper

import fr.xgouchet.elmyr.Forge
import id.aflah.universitieslist.domain.entity.University
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UniversityToDbEntityMapperTest {
    private lateinit var forger: Forge
    private lateinit var mapper: UniversityToDbEntityMapper

    @Before
    fun setUp() {
        forger = Forge()
        mapper = UniversityToDbEntityMapper()
    }

    @Test
    fun test_whenMapperFromResponseToEntity_thenEntityShouldBeMapped() {
        // inject
        val fakeCountry = forger.aString()
        val fakeName = forger.aString()
        val fakeStateProvince = forger.aString()
        val fakeAlphaTwoCode = forger.aString()
        val fakeDomains = forger.aList { forger.aString() }
        val fakeWebPages = forger.aList { forger.aString() }
        val fakeUpdatedAt = forger.aString()
        val fakeCreatedAt = forger.aString()
        val fakeResponse = University(
            country = fakeCountry,
            stateProvince = fakeStateProvince,
            alphaTwoCodes = fakeAlphaTwoCode,
            domains = fakeDomains,
            name = fakeName,
            webPages = fakeWebPages,
            updatedAt = fakeUpdatedAt,
            createdAt = fakeCreatedAt,
        )

        // when
        val mappedEntity = mapper.fromResponseToEntity(fakeResponse)

        // then
        with(mappedEntity) {
            assertEquals(country, fakeCountry)
            assertEquals(name, fakeName)
            assertEquals(stateProvince, fakeStateProvince)
            assertEquals(alphaTwoCode, fakeAlphaTwoCode)
            assertEquals(updatedAt, fakeUpdatedAt)
            assertEquals(createdAt, fakeCreatedAt)
            assertTrue(domains.orEmpty().isNotEmpty())
            assertTrue(webPages.orEmpty().isNotEmpty())
            domains.orEmpty().forEachIndexed { index, domain ->
                assertEquals(domain, fakeDomains[index])
            }
            webPages.orEmpty().forEachIndexed { index, webPage ->
                assertEquals(webPage, fakeWebPages[index])
            }
        }
    }

    @Test
    fun test_whenMapperFromResponseToEntities_thenEntitiesShouldBeMapped() {
        // inject
        val fakeCountry = forger.aString()
        val fakeName = forger.aString()
        val fakeStateProvince = forger.aString()
        val fakeAlphaTwoCode = forger.aString()
        val fakeDomains = forger.aList { forger.aString() }
        val fakeWebPages = forger.aList { forger.aString() }
        val fakeUpdatedAt = forger.aString()
        val fakeCreatedAt = forger.aString()
        val fakeResponse = University(
            country = fakeCountry,
            stateProvince = fakeStateProvince,
            alphaTwoCodes = fakeAlphaTwoCode,
            domains = fakeDomains,
            name = fakeName,
            webPages = fakeWebPages,
            updatedAt = fakeUpdatedAt,
            createdAt = fakeCreatedAt
        )
        val fakeResponseList = forger.aList {
            fakeResponse
        }

        // when
        val mappedEntities = mapper.fromResponsesToEntities(fakeResponseList)

        // then
        mappedEntities.forEachIndexed { _, mappedEntity ->
            with(mappedEntity) {
                assertEquals(country, fakeCountry)
                assertEquals(name, fakeName)
                assertEquals(stateProvince, fakeStateProvince)
                assertEquals(alphaTwoCode, fakeAlphaTwoCode)
                assertEquals(updatedAt, fakeUpdatedAt)
                assertEquals(createdAt, fakeCreatedAt)
                assertTrue(domains.orEmpty().isNotEmpty())
                assertTrue(webPages.orEmpty().isNotEmpty())
                domains.orEmpty().forEachIndexed { index, domain ->
                    assertEquals(domain, fakeDomains[index])
                }
                webPages.orEmpty().forEachIndexed { index, webPage ->
                    assertEquals(webPage, fakeWebPages[index])
                }
            }
        }
    }

    @Test
    fun test_whenMapperFromResponseToEntityANullResponse_thenEntityShouldReturnEmptyEntity() {
        // inject
        val fakeResponse = University()

        // when
        val mappedEntity = mapper.fromResponseToEntity(fakeResponse)

        // then
        with(mappedEntity) {
            assertEquals(country, "")
            assertEquals(name, "")
            assertEquals(stateProvince, "")
            assertEquals(alphaTwoCode, "")
            assertEquals(createdAt, "")
            assertEquals(updatedAt, "")
            assertTrue(domains.orEmpty().isEmpty())
            assertTrue(webPages.orEmpty().isEmpty())
        }
    }
}