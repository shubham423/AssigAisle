package com.example.assigaisle.data.models


import com.google.gson.annotations.SerializedName

data class GeneralInformation(
    @SerializedName("age")
    val age: Int,
    @SerializedName("first_name")
    val firstName: String,
)