package io.github.jerrymatera.projectsmanager.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArchivedStatus(
    @SerialName("Time")
    val time: String,
    @SerialName("Valid")
    val valid: Boolean
)