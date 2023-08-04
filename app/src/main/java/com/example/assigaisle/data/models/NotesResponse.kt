package com.example.assigaisle.data.models


import com.google.gson.annotations.SerializedName

data class NotesResponse(
    @SerializedName("invites")
    val invites: Invites,
    @SerializedName("likes")
    val likes: Likes
)