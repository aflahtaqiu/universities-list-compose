package id.aflah.universitieslist.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.domain.usecase.GetUniversitiesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUniversitiesListUseCase: GetUniversitiesListUseCase,
): ViewModel() {

    val universitiesListState: MutableState<ResultState<List<University>>?> = mutableStateOf(null)

    fun getUniversitiesList(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getUniversitiesListUseCase.execute(country).onEach {
                universitiesListState.value = it
            }.launchIn(viewModelScope)
        }
    }
}