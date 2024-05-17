package io.github.jerrymatera.projectsmanager.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("Email") val email: String,
    @SerialName("Phone") val phone: String,
    @SerialName("Username") val username: String,
    @SerialName("Uuid") val uuid: String
)