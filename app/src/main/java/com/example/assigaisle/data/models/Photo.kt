package com.example.assigaisle.data.models


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("photo")
    val photo: String,
    @SerializedName("selected")
    val selected: Boolean,
)