package com.example.nadercodechallenge1.ui.news

import androidx.lifecycle.ViewModel
import com.example.nadercodechallenge1.data.repository.NYTimesRepository
import com.example.nadercodechallenge1.internal.lazyDefered


class NewsViewModel(
    private val nyTimesRepository: NYTimesRepository
): ViewModel()  {
//    val section = ArticleSections.EMAILED
//    val period = ArticlePeriod.SEVEN
    val section = "emailed"
    val period = 7



    val article by lazyDefered {
        nyTimesRepository.getCurrentArticle(section, period)
    }
}