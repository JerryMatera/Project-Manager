package io.github.jerrymatera.projectsmanager.data.preferences

import kotlinx.coroutines.flow.Flow

interface UserPrefsStore {

    val accessToken: Flow<String?>
    suspend fun setAccessToken(token: String?)
}