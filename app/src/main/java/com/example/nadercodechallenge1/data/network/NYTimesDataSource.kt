package com.example.nadercodechallenge1.data.network

import androidx.lifecycle.LiveData
import com.example.nadercodechallenge1.data.network.responce.MostViewedSectionResponce

interface NYTimesDataSource{
    val downloadedCurrentArticle: LiveData<MostViewedSectionResponce>

    suspend fun fetchCurrentArticle(
        section: String,
        period: Int
    )
}