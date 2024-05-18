package io.github.jerrymatera.projectsmanager.presentation.screens.home

import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.network.model.User

data class HomeUIState(
    val user: User? = null,
    val userError: String = "",
    val projects: List<Project>? = emptyList(),
    val projectsError: String = ""
)