package com.example.nadercodechallenge1.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nadercodechallenge1.data.repository.NYTimesRepository
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val nyTimesRepository: NYTimesRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(nyTimesRepository) as T
    }
}