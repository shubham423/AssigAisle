package com.example.assigaisle.data.network

import com.example.assigaisle.data.models.LoginRequest
import com.example.assigaisle.data.models.LoginResponse
import com.example.assigaisle.data.models.NotesResponse
import com.example.assigaisle.data.models.VerifyOtpRequest
import com.example.assigaisle.data.models.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("users/phone_number_login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>

    @POST("users/verify_otp")
    suspend fun verifyOtp(@Body verifyOtpRequest: VerifyOtpRequest):Response<VerifyOtpResponse>

    @GET("users/test_profile_list")
    suspend fun getNotes(@Header("Authorization") authToken: String): Response<NotesResponse>

}