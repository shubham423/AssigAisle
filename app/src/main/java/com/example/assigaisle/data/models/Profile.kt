package com.example.assigaisle.data.models


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("general_information")
    val generalInformation: GeneralInformation,
    @SerializedName("photos")
    val photos: List<Photo>,
    )