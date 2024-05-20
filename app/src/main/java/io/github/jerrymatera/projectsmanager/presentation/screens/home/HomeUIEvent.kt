package io.github.jerrymatera.projectsmanager.presentation.screens.home

sealed class HomeUIEvent {
    data object GetUser : HomeUIEvent()
    data object GetProjects : HomeUIEvent()
    data object GetAllTasks : HomeUIEvent()
    data class UpdateProjectName(val name: String) : HomeUIEvent()
    data class UpdateProjectDescription(val description: String) : HomeUIEvent()
    data object CreateProject : HomeUIEvent()
    data object ClearProjectCreationError: HomeUIEvent()
}