package io.github.jerrymatera.projectsmanager.domain.repository

import io.github.jerrymatera.projectsmanager.data.network.model.AccessToken
import io.github.jerrymatera.projectsmanager.data.network.model.NetworkResponse
import io.github.jerrymatera.projectsmanager.data.network.model.User
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult

interface AuthenticationRepository {
    suspend fun register(
        username: String,
        email: String,
        phone: String,
        password: String
    ): NetworkResult<NetworkResponse<Nothing>>

    suspend fun login(
        usernameOrEmail: String,
        password: String
    ): NetworkResult<NetworkResponse<AccessToken>>

    suspend fun saveAccessToken(accessToken: String)
    suspend fun currentUser(): NetworkResult<NetworkResponse<User>>
}