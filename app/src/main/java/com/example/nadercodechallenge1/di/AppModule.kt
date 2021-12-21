package com.example.nadercodechallenge1.di

import android.content.Context
import com.example.nadercodechallenge1.NYTimesApplication
import com.example.nadercodechallenge1.data.db.CurrentArticleDao
import com.example.nadercodechallenge1.data.db.NYTimesDatabase
import com.example.nadercodechallenge1.data.network.*
import com.example.nadercodechallenge1.data.repository.NYTimesRepository
import com.example.nadercodechallenge1.data.repository.NYTimesRepositoryImpl
import com.example.nadercodechallenge1.ui.news.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    val context = NYTimesApplication.applicationContext()

    @Provides
    @Singleton
    fun getAppDatabase(context: Context): NYTimesDatabase {
        return NYTimesDatabase(context)
    }
    @Provides
    @Singleton
    fun getcurrentArticleDao(): CurrentArticleDao {
        return getAppDatabase(context).currentArticleDao()
    }


    @Provides
    @Singleton
    fun getConnectivityInterceptorImpl(): ConnectivityInterceptorImpl {
        return ConnectivityInterceptorImpl(context)
    }

    @Provides
    @Singleton
    fun getConnectivityInterceptor(): ConnectivityInterceptor {
        return getConnectivityInterceptorImpl()
    }

    @Provides
    @Singleton
    fun getNYApiService(): NYApiService {
        return NYApiService(getConnectivityInterceptor())
    }

    @Provides
    @Singleton
    fun getNYTimesDataSourceImpl(): NYTimesDataSourceImpl {
        return NYTimesDataSourceImpl(getNYApiService())
    }

    @Provides
    @Singleton
    fun getNYTimesDataSource(): NYTimesDataSource {
        return getNYTimesDataSourceImpl()
    }


    //
    @Provides
    @Singleton
    fun getNYTimesRepositoryImpl(): NYTimesRepositoryImpl {
        return NYTimesRepositoryImpl(getcurrentArticleDao(),getNYTimesDataSource())
    }

    @Provides
    @Singleton
    fun getNYTimesRepository(): NYTimesRepository {
        return getNYTimesRepositoryImpl()
    }

    @Provides
    @Singleton
    fun getNewsViewModelFactory(): NewsViewModelFactory {
        return NewsViewModelFactory(getNYTimesRepository())
    }
}