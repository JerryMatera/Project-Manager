package io.github.jerrymatera.projectsmanager.presentation.screens.project

sealed class ProjectUIEvent {
    data class UpdateTaskName(val name: String) : ProjectUIEvent()
    data class UpdateTaskDeadlineDate(val date: String) : ProjectUIEvent()
    data class UpdateArchiveProjectError(val s: String) : ProjectUIEvent()
    data object ShowCreateTaskScreen : ProjectUIEvent()
    data object GetProjectTasks : ProjectUIEvent()
    data object GetProjectArchivedTasks : ProjectUIEvent()
    data object AddTask : ProjectUIEvent()
    data object ArchiveProject : ProjectUIEvent()
}