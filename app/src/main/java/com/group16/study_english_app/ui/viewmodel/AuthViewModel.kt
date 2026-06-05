package com.group16.study_english_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.data.preferences.UserSessionDataStore
import com.group16.study_english_app.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepository: UserRepository,
    private val sessionDataStore: UserSessionDataStore
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    val isSrsDebugMode: StateFlow<Boolean> = sessionDataStore.srsDebugModeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            val userId = userRepository.loggedInUserIdFlow.firstOrNull()
            if (userId != null) {
                val user = userRepository.getLoggedInUser()
                if (user != null) {
                    userRepository.updateStreak(user.id)
                    _userState.value = UserState.Authenticated(user)
                } else {
                    _userState.value = UserState.Unauthenticated
                }
            } else {
                _userState.value = UserState.Unauthenticated
            }
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            userRepository.login(email, password)
                .onSuccess { user ->
                    _userState.value = UserState.Authenticated(user)
                    onSuccess()
                }
                .onFailure { error ->
                    _userState.value = UserState.Error(error.message ?: "Đăng nhập thất bại")
                }
        }
    }

    fun loginWithGoogle(email: String, name: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            userRepository.loginOrRegisterWithGoogle(email, name)
                .onSuccess { user ->
                    _userState.value = UserState.Authenticated(user)
                    onSuccess()
                }
                .onFailure { error ->
                    _userState.value = UserState.Error(error.message ?: "Đăng nhập Google thất bại")
                }
        }
    }

    fun register(
        email: String,
        password: String,
        name: String,
        targetGoal: String,
        level: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            userRepository.register(email, password, name, targetGoal, level)
                .onSuccess {
                    // Auto login after registration
                    login(email, password, onSuccess)
                }
                .onFailure { error ->
                    _userState.value = UserState.Error(error.message ?: "Đăng ký thất bại")
                }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            userRepository.logout()
            _userState.value = UserState.Unauthenticated
            onSuccess()
        }
    }

    fun updateUserProfile(name: String, targetGoal: String, level: String, dailyWordLimit: Int) {
        val currentState = _userState.value
        if (currentState is UserState.Authenticated) {
            viewModelScope.launch {
                userRepository.updateUserProfile(currentState.user.id, name, targetGoal, level, dailyWordLimit)
                val updatedUser = userRepository.getLoggedInUser()
                if (updatedUser != null) {
                    _userState.value = UserState.Authenticated(updatedUser)
                }
            }
        }
    }

    fun toggleSrsDebugMode(enabled: Boolean) {
        viewModelScope.launch {
            sessionDataStore.setSrsDebugMode(enabled)
        }
    }

    fun clearError() {
        if (_userState.value is UserState.Error) {
            _userState.value = UserState.Unauthenticated
        }
    }
}

sealed interface UserState {
    object Loading : UserState
    object Unauthenticated : UserState
    data class Authenticated(val user: UserEntity) : UserState
    data class Error(val message: String) : UserState
}
