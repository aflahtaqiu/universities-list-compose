package id.aflah.universitieslist.data.mapper

import fr.xgouchet.elmyr.Forge
import id.aflah.universitieslist.data.response.UniversityResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UniversityMapperTest {

    private lateinit var forger: Forge
    private lateinit var mapper: UniversityMapper

    @Before
    fun setUp() {
        forger = Forge()
        mapper = UniversityMapper()
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
        val fakeResponse = UniversityResponse(
            country = fakeCountry,
            stateProvince = fakeStateProvince,
            alphaTwoCode = fakeAlphaTwoCode,
            domains = fakeDomains,
            name = fakeName,
            webPages = fakeWebPages,
        )

        // when
        val mappedEntity = mapper.fromResponseToEntity(fakeResponse)

        // then
        with(mappedEntity) {
            assertEquals(country, fakeCountry)
            assertEquals(name, fakeName)
            assertEquals(stateProvince, fakeStateProvince)
            assertEquals(alphaTwoCodes, fakeAlphaTwoCode)
            domains.forEachIndexed { index, domain ->
                assertEquals(domain, fakeDomains[index])
            }
            webPages.forEachIndexed { index, webPage ->
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
        val fakeResponse = UniversityResponse(
            country = fakeCountry,
            stateProvince = fakeStateProvince,
            alphaTwoCode = fakeAlphaTwoCode,
            domains = fakeDomains,
            name = fakeName,
            webPages = fakeWebPages,
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
                assertEquals(alphaTwoCodes, fakeAlphaTwoCode)
                domains.forEachIndexed { index, domain ->
                    assertEquals(domain, fakeDomains[index])
                }
                webPages.forEachIndexed { index, webPage ->
                    assertEquals(webPage, fakeWebPages[index])
                }
            }
        }
    }

    @Test
    fun test_whenMapperFromResponseToEntityANullResponse_thenEntityShouldReturnEmptyEntity() {
        // inject
        val fakeResponse = UniversityResponse()

        // when
        val mappedEntity = mapper.fromResponseToEntity(fakeResponse)

        // then
        with(mappedEntity) {
            assertEquals(country, "")
            assertEquals(name, "")
            assertEquals(stateProvince, "")
            assertEquals(alphaTwoCodes, "")
            assertTrue(domains.isEmpty())
            assertTrue(webPages.isEmpty())
        }
    }
}