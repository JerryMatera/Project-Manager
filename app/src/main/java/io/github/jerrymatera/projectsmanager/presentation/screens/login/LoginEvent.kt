package io.github.jerrymatera.projectsmanager.presentation.screens.login

sealed class LoginEvent {
    data class UpdateUsernameOrEmail(val usernameOrEmail: String) : LoginEvent()
    data class UpdatePassword(val password: String) : LoginEvent()
    data object Login : LoginEvent()
}