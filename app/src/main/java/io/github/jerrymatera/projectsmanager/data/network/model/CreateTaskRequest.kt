package io.github.jerrymatera.projectsmanager.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskRequest(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("name")
    val name: String,
    @SerialName("project_uuid")
    val projectUuid: String? = null
)