package id.aflah.universitieslist.ui.screens

import fr.xgouchet.elmyr.Forge
import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.domain.usecase.GetUniversitiesListUseCase
import id.aflah.universitieslist.domain.usecase.SearchUniversitiesByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.reset
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.Exception

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var mockGetUniversitiesListUseCase: GetUniversitiesListUseCase

    @Mock
    lateinit var mockSearchUniversitiesByNameUseCase: SearchUniversitiesByNameUseCase

    @Mock
    lateinit var mockException: Exception

    private lateinit var viewModel: MainViewModel
    private lateinit var forger: Forge

    @Before
    fun setup() {
        viewModel = MainViewModel(mockGetUniversitiesListUseCase, mockSearchUniversitiesByNameUseCase)
        forger = Forge()
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        reset(mockSearchUniversitiesByNameUseCase, mockGetUniversitiesListUseCase, mockException)
    }

    @Test
    fun `getUniversitiesList should update universitiesListState when use case return success`() = runTest {
        // inject
        val universities = listOf(University())
        val resultState = ResultState.Success(universities)
        val fakeCountry = forger.aString()

        // given
        whenever(mockGetUniversitiesListUseCase.execute(fakeCountry)).thenReturn(flow { emit(resultState) })

        // when
        viewModel.getUniversitiesList(fakeCountry)
        advanceUntilIdle()

        // then
        verify(mockGetUniversitiesListUseCase).execute(fakeCountry)
        launch {
            delay(3000)
            assertNotNull(viewModel.universitiesListState.value)
            assertEquals(resultState, viewModel.universitiesListState.value)
        }
    }

    @Test
    fun `searchUniversitiesByName should update universitiesListState and searchQueryState when use case return success`() = runTest {
        // inject
        val universities = listOf(University())
        val resultState = ResultState.Success(universities)
        val fakeQuery = forger.aString()

        // given
        whenever(mockSearchUniversitiesByNameUseCase.execute(fakeQuery)).thenReturn(flow { emit(resultState) })

        // when
        viewModel.searchUniversitiesByName(fakeQuery)
        advanceUntilIdle()

        // then
        verify(mockSearchUniversitiesByNameUseCase).execute(fakeQuery)
        launch {
            delay(3000)
            assertNotNull(viewModel.universitiesListState.value)
            assertEquals(resultState, viewModel.universitiesListState.value)
            assertEquals(fakeQuery, viewModel.searchQueryState.value)
            assertTrue(viewModel.isSearchState.value)
        }
    }

    @Test
    fun `getUniversitiesList should update universitiesListState with error when failure occurs`() = runTest {
        // inject
        val resultState = ResultState.Error(mockException)
        val fakeCountry = forger.aString()
        val fakeErrorMessage = forger.aString()

        // given
        whenever(mockException.message).thenReturn(fakeErrorMessage)
        whenever(mockGetUniversitiesListUseCase.execute(fakeCountry)).thenReturn(flow { emit(resultState) })

        // when
        viewModel.getUniversitiesList(fakeCountry)
        advanceUntilIdle()

        // then
        verify(mockGetUniversitiesListUseCase).execute(fakeCountry)
        launch {
            delay(3000)
            assertNotNull(viewModel.universitiesListState.value)
            assertEquals(resultState, viewModel.universitiesListState.value)
        }
    }

    @Test
    fun `searchUniversitiesByName should update universitiesListState with error when failure occurs`() = runTest {
        // inject
        val resultState = ResultState.Error(mockException)
        val fakeQuery = forger.aString()
        val fakeErrorMessage = forger.aString()

        // given
        whenever(mockException.message).thenReturn(fakeErrorMessage)
        whenever(mockSearchUniversitiesByNameUseCase.execute(fakeQuery)).thenReturn(flow { emit(resultState) })

        // when
        viewModel.searchUniversitiesByName(fakeQuery)
        advanceUntilIdle()

        // then
        verify(mockSearchUniversitiesByNameUseCase).execute(fakeQuery)
        launch {
            delay(3000)
            assertNotNull(viewModel.universitiesListState.value)
            assertEquals(resultState, viewModel.universitiesListState.value)
        }
    }
}
