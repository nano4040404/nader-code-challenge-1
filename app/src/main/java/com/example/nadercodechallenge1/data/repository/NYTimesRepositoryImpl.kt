package com.example.nadercodechallenge1.data.repository

import androidx.lifecycle.LiveData
import com.example.nadercodechallenge1.data.db.CurrentArticleDao
import com.example.nadercodechallenge1.data.db.current.CurrentArticleEntry
import com.example.nadercodechallenge1.data.network.NYTimesDataSource
import com.example.nadercodechallenge1.data.network.responce.MostViewedSectionResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NYTimesRepositoryImpl @Inject constructor(
    private val  currentArticleDao: CurrentArticleDao,
    private val nyTimesDataSource: NYTimesDataSource
) : NYTimesRepository {
    init {
        nyTimesDataSource.apply {
            downloadedCurrentArticle.observeForever{newCurrentArticle ->
                persistFetchedCurrentArticle(newCurrentArticle)
            }
        }
    }
    override suspend fun getCurrentArticle(section: String, period: Int): LiveData<out List<CurrentArticleEntry>> {
        return withContext(Dispatchers.IO){
            initArticleData(section, period)
            return@withContext currentArticleDao.getArticles()
        }
    }

    private fun persistFetchedCurrentArticle(fetchedArticle: MostViewedSectionResponce){
        fun deleteOldArticleData() {
            currentArticleDao.deleteAllArticles()
        }

        GlobalScope.launch(Dispatchers.IO) {
            deleteOldArticleData()

            currentArticleDao.insert(fetchedArticle.results)
        }
    }

    private suspend fun initArticleData(section: String, period: Int){
        nyTimesDataSource.fetchCurrentArticle(
            section,period
        )
    }
//    private suspend fun fetchCurrentArticle(){
//        nyTimesDataSource.downloadedCurrentArticle
//    }

    override suspend fun getOfflineArticleData(): LiveData<out List<CurrentArticleEntry>>{
        return currentArticleDao.getArticles()
    }

//    private fun isFetchCurrentNeeded(lastFetchedTime: ZonedDateTime):Boolean{
//        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
//        return lastFetchedTime.isBefore(thirtyMinutesAgo)
//    }


}