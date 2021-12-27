package com.example.nadercodechallenge1.presentation.ui.settings

import androidx.lifecycle.ViewModel
import com.example.nadercodechallenge1.domain.NyUseCase
import com.example.nadercodechallenge1.internal.lazyDefered
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val nyUseCase: NyUseCase
) : ViewModel() {
    //suspend fun getData(): LiveData<out List<CurrentArticleEntry>> = repo.getOfflineArticleData()

    val article by lazyDefered {
        nyUseCase.getAcrticlesOffline()
    }

}