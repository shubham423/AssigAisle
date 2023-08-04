package com.example.assigaisle.data.models


import com.google.gson.annotations.SerializedName

data class Likes(
    @SerializedName("profiles")
    val profiles: List<LikesProfile>
)