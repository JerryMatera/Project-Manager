package io.github.jerrymatera.projectsmanager.presentation.navigation

sealed class ScreenRoutes(val route: String) {
    data object Login : ScreenRoutes("login")
    data object Register : ScreenRoutes("register")
    data object Home : ScreenRoutes("home")
}