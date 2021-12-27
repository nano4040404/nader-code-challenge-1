package com.example.nadercodechallenge1.domain

import androidx.lifecycle.LiveData
import com.example.nadercodechallenge1.data.db.current.CurrentArticleEntry
import com.example.nadercodechallenge1.data.repository.NYTimesRepository
import javax.inject.Inject

class NyUseCase @Inject constructor(
    private val nyTimesRepository: NYTimesRepository
) {
    suspend fun getCurrentArticle(section: String, period: Int): LiveData<out List<CurrentArticleEntry>> {
        return nyTimesRepository.getCurrentArticle(section, period)
    }

    suspend fun getAcrticlesOffline(): LiveData<out List<CurrentArticleEntry>>{
        return nyTimesRepository.getOfflineArticleData()
    }
}