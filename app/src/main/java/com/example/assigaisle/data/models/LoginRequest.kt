package com.example.assigaisle.data.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("number")
    val number: String
)
