package io.github.jerrymatera.projectsmanager.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Login

@Serializable
data object Register

@Serializable
data object Home

@Serializable
data object Archives

@Serializable
data class ProjectRoute(
    val createdAt: String,
    val description: String,
    val name: String,
    val userUuid: String,
    val uuid: String,
    val archiveTime: String,
    val archiveValid: Boolean
)
