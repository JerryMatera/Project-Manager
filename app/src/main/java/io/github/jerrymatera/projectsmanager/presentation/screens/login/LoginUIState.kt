package io.github.jerrymatera.projectsmanager.presentation.screens.login

data class LoginUIState(
    val usernameOrEmail: String = "",
    val usernameOrEmailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val loginError: String = "",
    val authenticated: Boolean = false,
)