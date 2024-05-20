package io.github.jerrymatera.projectsmanager.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<out T : Any?>(
    val code: Int,
    val message: String,
    val data: T? = null
)
