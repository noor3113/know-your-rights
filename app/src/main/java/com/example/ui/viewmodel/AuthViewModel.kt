package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserEntity
import com.example.data.local.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val selectedTab: Int = 0, // 0 = Login, 1 = Sign Up
    val loginEmail: String = "",
    val loginPassword: String = "",
    val signUpName: String = "",
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val currentUser: UserEntity? = null,
    val isGuest: Boolean = false
)

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun setSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTab = tab, errorMessage = null, successMessage = null) }
    }

    fun onLoginEmailChanged(email: String) {
        _uiState.update { it.copy(loginEmail = email, errorMessage = null) }
    }

    fun onLoginPasswordChanged(password: String) {
        _uiState.update { it.copy(loginPassword = password, errorMessage = null) }
    }

    fun onSignUpNameChanged(name: String) {
        _uiState.update { it.copy(signUpName = name, errorMessage = null) }
    }

    fun onSignUpEmailChanged(email: String) {
        _uiState.update { it.copy(signUpEmail = email, errorMessage = null) }
    }

    fun onSignUpPasswordChanged(password: String) {
        _uiState.update { it.copy(signUpPassword = password, errorMessage = null) }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun login(onSuccess: () -> Unit) {
        val state = _uiState.value
        if (state.loginEmail.isBlank() || state.loginPassword.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Please enter both email and password") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = repository.loginUser(state.loginEmail, state.loginPassword)
            result.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        currentUser = user,
                        isGuest = false,
                        loginPassword = ""
                    )
                }
                onSuccess()
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.localizedMessage ?: "Login failed. Check your credentials."
                    )
                }
            }
        }
    }

    fun signUp(onSuccess: () -> Unit) {
        val state = _uiState.value
        if (state.signUpName.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Please enter your full name") }
            return
        }
        if (state.signUpEmail.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Please enter your email address") }
            return
        }
        if (state.signUpPassword.length < 6) {
            _uiState.update { it.copy(errorMessage = "Password must be at least 6 characters") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = repository.registerUser(
                name = state.signUpName,
                email = state.signUpEmail,
                password = state.signUpPassword
            )
            result.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        currentUser = user,
                        isGuest = false,
                        signUpPassword = "",
                        successMessage = "Account created successfully!"
                    )
                }
                onSuccess()
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.localizedMessage ?: "Could not create account"
                    )
                }
            }
        }
    }

    fun skipAuth(onSuccess: () -> Unit) {
        _uiState.update {
            it.copy(
                currentUser = null,
                isGuest = true,
                errorMessage = null
            )
        }
        onSuccess()
    }

    fun logout(onLoggedOut: () -> Unit) {
        _uiState.update {
            AuthUiState() // Reset
        }
        onLoggedOut()
    }
}

class AuthViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
