package com.example.nadercodechallenge1.data.db.entity


import com.google.gson.annotations.SerializedName

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
)