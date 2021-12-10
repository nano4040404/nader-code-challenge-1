package com.example.nadercodechallenge1.data.network.responce


import com.example.nadercodechallenge1.data.db.entity.Result
import com.google.gson.annotations.SerializedName

data class MostViewedSectionResponce(
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    val results: List<Result>
)