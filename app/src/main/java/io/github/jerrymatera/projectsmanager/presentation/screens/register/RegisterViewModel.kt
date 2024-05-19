package io.github.jerrymatera.projectsmanager.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {
    private val _state = MutableStateFlow(RegisterUIState())
    val state: StateFlow<RegisterUIState> = _state

    fun performEvent(event: RegisterUIEvent) {
        when (event) {
            RegisterUIEvent.Register -> register()
            is RegisterUIEvent.UpdateConfirmPassword -> {
                _state.value = _state.value.copy(confirmPassword = event.password)
            }

            is RegisterUIEvent.UpdatePassword -> {
                _state.value = _state.value.copy(password = event.password)
            }

            is RegisterUIEvent.UpdateEmail -> {
                _state.value = _state.value.copy(email = event.email)
            }

            is RegisterUIEvent.UpdatePhone -> {
                _state.value = _state.value.copy(phoneNumber = event.phoneNumber)
            }

            is RegisterUIEvent.UpdateUserName -> {
                _state.value = _state.value.copy(userName = event.userName)
            }
        }
    }

    private fun register() = viewModelScope.launch {
        if (_state.value.password != _state.value.confirmPassword) {
            return@launch
        }
        val result = authenticationRepository.register(
            username = _state.value.userName,
            email = _state.value.email,
            phone = _state.value.phoneNumber,
            password = _state.value.password,
        )
        when (result) {
            is NetworkResult.Error -> {
                _state.value =
                    _state.value.copy(formError = result.body?.message ?: "Correct form Errors")
            }

            is NetworkResult.Success -> {
                _state.value = _state.value.copy(registerSuccess = true)
            }
        }
    }
}