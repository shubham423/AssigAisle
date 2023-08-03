package com.example.assigaisle.data.network

import com.example.assigaisle.data.models.LoginRequest
import com.example.assigaisle.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/users/phone_number_login")
    suspend fun getOtp(@Body loginRequest: LoginRequest):Response<LoginResponse>
}