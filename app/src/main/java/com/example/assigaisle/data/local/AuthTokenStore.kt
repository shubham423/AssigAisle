package com.example.assigaisle.data.local

interface AuthTokenStore {
    suspend fun saveAuthToken(token: String)
    suspend fun getAuthToken(): String
}