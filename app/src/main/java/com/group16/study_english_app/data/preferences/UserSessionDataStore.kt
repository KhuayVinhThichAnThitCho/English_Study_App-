package com.group16.study_english_app.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_session")

class UserSessionDataStore(private val context: Context) {
    companion object {
        private val USER_ID = longPreferencesKey("user_id")
        private val JWT_TOKEN = stringPreferencesKey("jwt_token")
        private val SRS_DEBUG_MODE = stringPreferencesKey("srs_debug_mode") // "days" or "minutes"
    }

    val userIdFlow: Flow<Long?> = context.dataStore.data.map { preferences ->
        val id = preferences[USER_ID]
        if (id == null || id == -1L) null else id
    }

    val tokenFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[JWT_TOKEN]
    }

    val srsDebugModeFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[SRS_DEBUG_MODE] == "minutes"
    }

    suspend fun saveSession(userId: Long, token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
            preferences[JWT_TOKEN] = token
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = -1L
            preferences[JWT_TOKEN] = ""
        }
    }

    suspend fun setSrsDebugMode(debugMinutes: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SRS_DEBUG_MODE] = if (debugMinutes) "minutes" else "days"
        }
    }
}
