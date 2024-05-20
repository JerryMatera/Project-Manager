package io.github.jerrymatera.projectsmanager.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authenticationRepository: AuthenticationRepository,
) :
    ViewModel() {
    private val _state = MutableStateFlow(value = LoginUIState())
    val state: StateFlow<LoginUIState> = _state

    fun performEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                login()
            }

            is LoginEvent.UpdatePassword -> {
                _state.value = _state.value.copy(password = event.password)
            }

            is LoginEvent.UpdateUsernameOrEmail -> {
                _state.value = _state.value.copy(email = event.usernameOrEmail)
            }
        }
    }

    private fun login() = viewModelScope.launch {
        val result = authenticationRepository.login(
            usernameOrEmail = _state.value.email,
            password = _state.value.password
        )
        when (result) {
            is NetworkResult.Error -> {
                _state.value =
                    _state.value.copy(loginError = result.body?.message ?: "Something happened")
            }

            is NetworkResult.Success -> {
                authenticationRepository.saveAccessToken(result.body.data!!.accessToken)
                _state.value = _state.value.copy(authenticated = true)
            }
        }
    }

}