package com.example.assigaisle.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: Boolean
)