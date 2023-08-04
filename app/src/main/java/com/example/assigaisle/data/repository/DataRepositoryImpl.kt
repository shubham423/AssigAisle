package com.example.assigaisle.data.repository

import com.example.assigaisle.data.models.LoginRequest
import com.example.assigaisle.data.models.LoginResponse
import com.example.assigaisle.data.models.NotesResponse
import com.example.assigaisle.data.models.VerifyOtpRequest
import com.example.assigaisle.data.models.VerifyOtpResponse
import com.example.assigaisle.data.network.ApiService
import com.example.assigaisle.domain.repository.DataRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(private val apiService: ApiService):DataRepository{
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
       return apiService.login(loginRequest)
    }

    override suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Response<VerifyOtpResponse> {
        return apiService.verifyOtp(verifyOtpRequest)
    }

    override suspend fun getNotes(authToken: String): Response<NotesResponse> {
        return apiService.getNotes(authToken)
    }
}