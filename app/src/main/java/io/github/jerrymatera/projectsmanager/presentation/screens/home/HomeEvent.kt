package io.github.jerrymatera.projectsmanager.presentation.screens.home

sealed class HomeEvent {
    data object GetUser : HomeEvent()
    data object GetProjects : HomeEvent()
    data object GetAllTasks : HomeEvent()
    data class UpdateProjectName(val name: String) : HomeEvent()
    data class UpdateProjectDescription(val description: String) : HomeEvent()
    data object CreateProject : HomeEvent()
    data object ClearProjectCreationError: HomeEvent()
}