package com.example.nadercodechallenge1.presentation

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


//class NYTimesApplication: Application(), KodeinAware {
//    override val kodein = Kodein.lazy {
//        import(androidXModule(this@NYTimesApplication))
//
//        bind() from singleton { NYTimesDatabase(instance()) }
//        bind() from singleton { instance<NYTimesDatabase>().currentArticleDao() }
//        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
//        bind() from singleton { NYApiService(instance()) }
//        bind<NYTimesDataSource>() with singleton { NYTimesDataSourceImpl(instance()) }
//        bind<NYTimesRepository>() with singleton { NYTimesRepositoryImpl(instance(), instance()) }
//        bind() from provider { NewsViewModelFactory(instance()) }
//    }
//
//}

@HiltAndroidApp
class NYTimesApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: NYTimesApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


}