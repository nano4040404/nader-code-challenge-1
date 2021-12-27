package com.example.nadercodechallenge1.presentation.ui.news

import androidx.lifecycle.ViewModel
import com.example.nadercodechallenge1.domain.NyUseCase
import com.example.nadercodechallenge1.internal.lazyDefered


class NewsViewModel(
    //private val nyTimesRepository: NYTimesRepository,
    private val nyUseCase: NyUseCase
): ViewModel()  {
//    val section = ArticleSections.EMAILED
//    val period = ArticlePeriod.SEVEN
    val section = "emailed"
    val period = 7

    val article by lazyDefered {
        nyUseCase.getCurrentArticle(section,period)
    }

//    val article by lazyDefered {
//        nyTimesRepository.getCurrentArticle(section, period)
//    }
}