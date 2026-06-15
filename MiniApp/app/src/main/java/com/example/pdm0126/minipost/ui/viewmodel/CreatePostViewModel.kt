package com.example.pdm0126.minipost.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pdm0126.minipost.data.PostRepository
import com.example.pdm0126.minipost.data.PostRepositoryImpl
import com.example.pdm0126.minipost.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CreatePostUiState(
    val title: String = "",
    val body: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val createdPost: Post? = null,
    val errorMessage: String? = null
)

class CreatePostViewModel(
    private val repository: PostRepository = PostRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreatePostUiState())
    val uiState: StateFlow<CreatePostUiState> = _uiState.asStateFlow()

    fun onTitleChanged(newTitle: String) {
        _uiState.value = _uiState.value.copy(title = newTitle)
    }

    fun onBodyChanged(newBody: String) {
        _uiState.value = _uiState.value.copy(body = newBody)
    }

    fun createPost() {
        val currentState = _uiState.value
        if (currentState.title.isBlank() || currentState.body.isBlank()) {
            _uiState.value = currentState.copy(errorMessage = "Completa todos los campos")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                isSuccess = false
            )

            repository.createPost(
                title = currentState.title,
                body = currentState.body
            )
                .onSuccess { post ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        createdPost = post,
                        title = "",
                        body = ""
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Error al crear el post"
                    )
                }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(isSuccess = false, createdPost = null)
    }
}