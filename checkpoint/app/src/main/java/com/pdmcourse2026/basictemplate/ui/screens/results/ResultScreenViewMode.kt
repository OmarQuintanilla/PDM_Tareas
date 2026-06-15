package com.pdmcourse2026.basictemplate.ui.screens.results



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse2026.basictemplate.RankeUcaApplication
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepository
import com.pdmcourse2026.basictemplate.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class ResultsUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val places: List<Place> = emptyList(),
    val errorMessage: String? = null
)

class ResultScreenViewModel(
    private val repository: RankeUcaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadResults(isRefresh = false)
    }

    fun refreshResults() {
        loadResults(isRefresh = true)
    }

    private fun loadResults(isRefresh: Boolean) {
        _uiState.update {
            if (isRefresh) it.copy(isRefreshing = true, errorMessage = null)
            else it.copy(isLoading = true, errorMessage = null)
        }

        /*viewModelScope.launch {
            repository.getOptions()
                .onSuccess { placesList ->
                    // Ordenamos de mayor a menor como pide el requerimiento
                    val sortedPlaces = placesList.sortedByDescending { it.votes }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            places = sortedPlaces
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            errorMessage = exception.message ?: "Error al cargar los resultados"
                        )
                    }
                }
        }*/
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RankeUcaApplication
                ResultScreenViewModel(app.appProvider.provideOptionRepository())
            }
        }
    }
}