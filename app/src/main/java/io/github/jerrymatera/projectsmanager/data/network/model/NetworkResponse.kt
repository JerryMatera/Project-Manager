package io.github.jerrymatera.projectsmanager.data.network.model

data class NetworkResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)
