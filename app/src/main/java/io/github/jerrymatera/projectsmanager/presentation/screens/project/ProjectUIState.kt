package io.github.jerrymatera.projectsmanager.presentation.screens.project

import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.network.model.Task


data class ProjectUIState(
    val project: Project? = null,
    val projectError: String = "",
    val projectTasks: List<Task> = emptyList(),
    val projectTasksError: String = "",
    val createTask: Boolean = false,
    val newTaskName: String = "",
    val newTaskDeadlineDate: String = "",
    val newTaskDeadlineTime: String = "",
    val taskCreationSuccess: Boolean = false,
    val taskCreationError: String = "",
    val archivedTasks: List<Task> = emptyList(),
)
