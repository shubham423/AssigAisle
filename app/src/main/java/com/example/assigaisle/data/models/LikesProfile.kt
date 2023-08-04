package com.example.assigaisle.data.models

import com.google.gson.annotations.SerializedName

data class LikesProfile(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("avatar")
    val avatar: String
)
