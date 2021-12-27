package com.example.nadercodechallenge1.presentation.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nadercodechallenge1.domain.NyUseCase
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val nyUseCase: NyUseCase
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(nyUseCase) as T
    }
}