package io.github.jerrymatera.projectsmanager.presentation.screens.register

data class RegisterUIState(
    val userName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val formError: String = "",
    val registerSuccess: Boolean = false
)