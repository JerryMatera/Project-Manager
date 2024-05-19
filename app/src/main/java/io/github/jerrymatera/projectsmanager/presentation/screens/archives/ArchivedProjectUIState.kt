package io.github.jerrymatera.projectsmanager.presentation.screens.archives

import io.github.jerrymatera.projectsmanager.data.network.model.Project

data class ArchivedProjectUIState(
    val archivedProjects: List<Project> = emptyList(),
    val archiveError: String = ""
)

