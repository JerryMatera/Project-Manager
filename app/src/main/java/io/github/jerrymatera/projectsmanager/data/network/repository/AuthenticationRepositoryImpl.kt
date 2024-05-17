package io.github.jerrymatera.projectsmanager.data.network.repository

import io.github.jerrymatera.projectsmanager.data.network.ResponseHandler
import io.github.jerrymatera.projectsmanager.data.network.model.AccessToken
import io.github.jerrymatera.projectsmanager.data.network.model.LoginRequest
import io.github.jerrymatera.projectsmanager.data.network.model.NetworkResponse
import io.github.jerrymatera.projectsmanager.data.network.model.RegisterRequest
import io.github.jerrymatera.projectsmanager.data.network.model.User
import io.github.jerrymatera.projectsmanager.data.preferences.UserPrefsStore
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val responseHandler: ResponseHandler,
    private val userPrefsStore: UserPrefsStore
) :
    AuthenticationRepository {
    override suspend fun register(
        username: String,
        email: String,
        phone: String,
        password: String
    ): NetworkResult<NetworkResponse<Nothing>> =
        responseHandler.post(
            urlPathSegments = listOf("register"),
            body = RegisterRequest(
                username = username,
                email = email,
                phone = phone,
                password = password,
                confirmPassword = password
            )
        )


    override suspend fun login(
        usernameOrEmail: String,
        password: String
    ): NetworkResult<NetworkResponse<AccessToken>> = responseHandler.post(
        urlPathSegments = listOf("login"),
        body = LoginRequest(
            usernameOrEmail = usernameOrEmail,
            password = password
        )
    )

    override suspend fun saveAccessToken(accessToken: String) {
        userPrefsStore.setAccessToken(accessToken)
    }

    override suspend fun currentUser(): NetworkResult<NetworkResponse<User>> = responseHandler.get(
        listOf("current_user")
    )
}