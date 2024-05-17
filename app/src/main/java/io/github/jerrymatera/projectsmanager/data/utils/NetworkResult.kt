package io.github.jerrymatera.projectsmanager.data.utils

sealed class NetworkResult<T> {
    data class Success<T>(val body: T) : NetworkResult<T>()
    data class Error<T>(val body: T? = null, val exception: Exception) : NetworkResult<T>()
}