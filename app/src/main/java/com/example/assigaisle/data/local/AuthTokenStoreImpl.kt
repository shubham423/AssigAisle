package com.example.assigaisle.data.local


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthTokenStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : AuthTokenStore {

    companion object {
        val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    override suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_KEY] = token
        }
    }

    override suspend fun getAuthToken(): String {
        val preferences = dataStore.data.first()
        return preferences[AUTH_TOKEN_KEY] ?: ""
    }
}
