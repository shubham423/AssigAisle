package com.example.assigaisle.data.models


import com.google.gson.annotations.SerializedName

data class Invites(
    @SerializedName("profiles")
    val profiles: List<Profile>,
)