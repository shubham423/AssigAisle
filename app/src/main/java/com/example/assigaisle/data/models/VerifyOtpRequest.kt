package com.example.assigaisle.data.models

import com.google.gson.annotations.SerializedName

data class VerifyOtpRequest(
    @SerializedName("number")
    val number: String,
    @SerializedName("otp")
    val otp: String,
)