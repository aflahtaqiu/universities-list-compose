package id.aflah.universitieslist.domain.usecase

import fr.xgouchet.elmyr.Forge
import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.domain.repo.UniversityRepository
import kotlinx.coroutines.flow.Flow
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any

@RunWith(MockitoJUnitRunner::class)
class GetUniversitiesListUseCaseTest {

    private lateinit var useCase: GetUniversitiesListUseCase

    @Mock
    lateinit var mockRepository: UniversityRepository

    @Mock
    lateinit var mockResult: Flow<ResultState<List<University>>>

    @Before
    fun setUp() {
        useCase = GetUniversitiesListUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        BDDMockito.reset(
            mockResult,
            mockRepository,
        )
    }

    @Test
    fun `test given GetUniversitiesListUseCase with non-null params when use case build then should call getUniversitiesList with non-null params`() {
        // inject
        val fakeParams = Forge().aString()

        // given
        BDDMockito.given(mockRepository.getUniversitiesList(fakeParams))
            .willReturn(mockResult)

        // when
        useCase.execute(fakeParams)

        // then
        BDDMockito.then(mockRepository).should().getUniversitiesList(fakeParams)
        BDDMockito.then(mockRepository).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `test given GetUniversitiesListUseCase with null params when use case build then should call getUniversitiesList with empty string params`() {
        // given
        BDDMockito.given(mockRepository.getUniversitiesList(any()))
            .willReturn(mockResult)

        // when
        useCase.execute(null)

        // then
        BDDMockito.then(mockRepository).should().getUniversitiesList("")
        BDDMockito.then(mockRepository).shouldHaveNoMoreInteractions()
    }
}