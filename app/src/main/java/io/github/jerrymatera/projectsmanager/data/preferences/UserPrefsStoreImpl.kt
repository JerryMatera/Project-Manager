package io.github.jerrymatera.projectsmanager.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPrefsStoreImpl(private val context: Context) : UserPrefsStore {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
        private val USER_ACCESS_TOKEN = stringPreferencesKey("user_access_token")
    }

    override val accessToken: Flow<String?> = context.dataStore.data.map {
        it[USER_ACCESS_TOKEN]
    }

    override suspend fun setAccessToken(token: String?) {
        context.dataStore.edit {
            if (token.isNullOrBlank()) {
                it.remove(USER_ACCESS_TOKEN)
            } else {
                it[USER_ACCESS_TOKEN] = token
            }
        }
    }
}