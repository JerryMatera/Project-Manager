package io.github.jerrymatera.projectsmanager.presentation.screens.project

sealed class ProjectUIEvent {
    data object GetProjectTasks : ProjectUIEvent()
    data object AddTask : ProjectUIEvent()
}