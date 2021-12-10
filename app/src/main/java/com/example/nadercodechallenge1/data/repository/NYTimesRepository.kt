package com.example.nadercodechallenge1.data.repository

import androidx.lifecycle.LiveData
import com.example.nadercodechallenge1.data.db.current.ArticleEntry

interface NYTimesRepository {
    suspend fun getCurrentArticle(section: String, period: Int): LiveData<out List<ArticleEntry>>
}