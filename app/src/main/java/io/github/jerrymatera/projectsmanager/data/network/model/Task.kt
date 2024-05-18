package io.github.jerrymatera.projectsmanager.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("ArchivedAt") val archivedStatus: ArchivedStatus,
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("Deadline") val deadline: String,
    @SerialName("Name") val name: String,
    @SerialName("ProjectName") val projectName: String,
    @SerialName("ProjectUuid") val projectUuid: String,
    @SerialName("Uuid") val uuid: String
)