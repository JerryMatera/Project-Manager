package io.github.jerrymatera.projectsmanager.presentation.screens.register

sealed class RegisterUIEvent {
    data class UpdateUserName(val userName: String) : RegisterUIEvent()
    data class UpdateEmail(val email: String) : RegisterUIEvent()
    data class UpdatePhone(val phoneNumber: String) : RegisterUIEvent()
    data class UpdatePassword(val password: String) : RegisterUIEvent()
    data class UpdateConfirmPassword(val password: String) : RegisterUIEvent()
    data object Register : RegisterUIEvent()
}