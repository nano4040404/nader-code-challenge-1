package com.example.nadercodechallenge1.ui.settings

import androidx.lifecycle.ViewModel
import com.example.nadercodechallenge1.data.repository.NYTimesRepository
import com.example.nadercodechallenge1.internal.lazyDefered
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repo: NYTimesRepository
) : ViewModel() {
    //suspend fun getData(): LiveData<out List<CurrentArticleEntry>> = repo.getOfflineArticleData()

    val article by lazyDefered {
        repo.getOfflineArticleData()
    }

}