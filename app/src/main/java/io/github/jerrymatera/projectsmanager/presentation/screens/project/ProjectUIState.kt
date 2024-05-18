package io.github.jerrymatera.projectsmanager.presentation.screens.project

import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.network.model.Task

data class ProjectUIState(
    val project: Project? = null,
    val projectError: String = "",
    val projectTasks: List<Task>? = emptyList(),
    val projectTasksError: String = ""
)
