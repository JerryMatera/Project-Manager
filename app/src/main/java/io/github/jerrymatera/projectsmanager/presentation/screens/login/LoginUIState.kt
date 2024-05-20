package io.github.jerrymatera.projectsmanager.presentation.screens.login

data class LoginUIState(
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val loginError: String = "",
    val authenticated: Boolean = false,
)