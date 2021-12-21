package com.example.nadercodechallenge1.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nadercodechallenge1.data.network.responce.MostViewedSectionResponce
import com.example.nadercodechallenge1.internal.NoConnectivityExceptions
import javax.inject.Inject

class NYTimesDataSourceImpl @Inject constructor(
    private val nyApiService: NYApiService
) : NYTimesDataSource {

    private val _downloadedCurrentArticle= MutableLiveData<MostViewedSectionResponce>()

    override val downloadedCurrentArticle: LiveData<MostViewedSectionResponce>
        get() = _downloadedCurrentArticle

    override suspend fun fetchCurrentArticle(section: String, period: Int) {
        try{
            val fetchedCurrentArticle = nyApiService
                .getMostViewed(section, period)
                .await()
            _downloadedCurrentArticle.postValue(fetchedCurrentArticle)
        }
        catch (e: NoConnectivityExceptions){
            Log.e("Connectivity","No internet connection",e)
        }
    }
}