package com.example.nadercodechallenge1.di

import android.content.Context
import com.example.nadercodechallenge1.data.db.CurrentArticleDao
import com.example.nadercodechallenge1.data.db.NYTimesDatabase
import com.example.nadercodechallenge1.data.network.*
import com.example.nadercodechallenge1.data.repository.NYTimesRepository
import com.example.nadercodechallenge1.data.repository.NYTimesRepositoryImpl
import com.example.nadercodechallenge1.domain.NyUseCase
import com.example.nadercodechallenge1.presentation.NYTimesApplication
import com.example.nadercodechallenge1.presentation.ui.editprofile.EditProfileViewModel
import com.example.nadercodechallenge1.presentation.ui.more.MoreSettingsViewModel
import com.example.nadercodechallenge1.presentation.ui.news.ListPaddingDecoration
import com.example.nadercodechallenge1.presentation.ui.news.NewsViewModelFactory
import com.example.nadercodechallenge1.presentation.ui.settings.SettingsViewModel
import com.example.nadercodechallenge1.utils.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        return getApiService()
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
        return NewsViewModelFactory(getNyUseCase())
    }

    @Provides
    @Singleton
    fun getSettingsViewModel(): SettingsViewModel {
        return SettingsViewModel(getNyUseCase())
    }
    @Provides
    @Singleton
    fun getMoreSettingsViewModel(): MoreSettingsViewModel {
        return MoreSettingsViewModel()
    }

    @Provides
    @Singleton
    fun getNyUseCase(): NyUseCase {
        return NyUseCase(getNYTimesRepository())
    }

    @Singleton
    @Provides
    fun getApiService(): NYApiService {
        val requestInterceptor = Interceptor{chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api-key", AppConstants.API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(getConnectivityInterceptor())
            .build()

        return  Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(AppConstants.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NYApiService::class.java)
    }
    @Singleton
    @Provides
    fun getListPaddingDecoration(): ListPaddingDecoration{
        return ListPaddingDecoration(context)
    }
    @Singleton
    @Provides
    fun getEditProfileViewModel(): EditProfileViewModel = EditProfileViewModel(context)
}