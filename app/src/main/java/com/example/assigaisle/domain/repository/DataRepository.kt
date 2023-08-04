package com.example.assigaisle.domain.repository

import com.example.assigaisle.data.models.LoginRequest
import com.example.assigaisle.data.models.LoginResponse
import com.example.assigaisle.data.models.VerifyOtpRequest
import com.example.assigaisle.data.models.VerifyOtpResponse
import retrofit2.Response

interface DataRepository {
    suspend fun login(loginRequest: LoginRequest):Response<LoginResponse>
    suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest):Response<VerifyOtpResponse>
}