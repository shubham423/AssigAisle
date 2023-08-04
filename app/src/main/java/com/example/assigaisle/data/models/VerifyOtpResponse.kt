package com.example.assigaisle.data.models

import com.google.gson.annotations.SerializedName

data class VerifyOtpResponse(
    @SerializedName("token")
    val authToken: String?
)