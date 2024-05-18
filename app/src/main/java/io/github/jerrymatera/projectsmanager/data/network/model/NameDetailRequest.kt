package io.github.jerrymatera.projectsmanager.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameDetailRequest(
    @SerialName("description")
    val description: String,
    @SerialName("name")
    val name: String
)