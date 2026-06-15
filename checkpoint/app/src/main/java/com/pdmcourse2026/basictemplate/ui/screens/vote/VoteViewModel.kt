package com.pdmcourse2026.basictemplate.ui.screens.vote


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse2026.basictemplate.RankeUcaApplication
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepository
import com.pdmcourse2026.basictemplate.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class VoteUiState(
    val isLoading: Boolean = true,
    val places: List<Place> = emptyList(),
    val errorMessage: String? = null,
    val isVoteSuccessful: Boolean = false,
    val votedPlaceId: Int? = null
)

class VoteViewModel(
    private val repository: RankeUcaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VoteUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPlaces()
    }

    private fun loadPlaces() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

       /* viewModelScope.launch {
            repository.getOptions()
                .onSuccess { placesList ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            places = placesList
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "Error desconocido al cargar lugares"
                        )
                    }
                }
        }
    */}

    fun voteForPlace(placeId: Int) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        /* viewModelScope.launch {
            repository.vote(placeId)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isVoteSuccessful = true,
                            votedPlaceId = placeId
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "Error al registrar el voto"
                        )
                    }
                }
        }
    */}

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RankeUcaApplication
                VoteViewModel(app.appProvider.provideOptionRepository())
            }
        }
    }
}