package io.github.jerrymatera.projectsmanager.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    @SerialName("ArchivedAt") val archivedStatus: ArchivedStatus,
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("Description") val description: String,
    @SerialName("Name") val name: String,
    @SerialName("UserUuid") val userUuid: String,
    @SerialName("Uuid") val uuid: String
)