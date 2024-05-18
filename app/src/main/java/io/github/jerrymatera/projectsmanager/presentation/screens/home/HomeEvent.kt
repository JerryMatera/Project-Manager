package io.github.jerrymatera.projectsmanager.presentation.screens.home

sealed class HomeEvent {
    data object GetUser : HomeEvent()
    data object GetProjects : HomeEvent()
    data object GetAllTasks : HomeEvent()
}