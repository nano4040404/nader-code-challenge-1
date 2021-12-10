package com.example.nadercodechallenge1

import android.app.Application
import com.example.nadercodechallenge1.data.db.NYTimesDatabase
import com.example.nadercodechallenge1.data.repository.NYTimesRepository
import com.example.nadercodechallenge1.data.repository.NYTimesRepositoryImpl
import com.example.nadercodechallenge1.data.network.*
import com.example.nadercodechallenge1.ui.news.NewsViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class NYTimesApplication: Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@NYTimesApplication))

        bind() from singleton { NYTimesDatabase(instance()) }
        bind() from singleton { instance<NYTimesDatabase>().currentArticleDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { NYApiService(instance()) }
        bind<NYTimesDataSource>() with singleton { NYTimesDataSourceImpl(instance()) }
        bind<NYTimesRepository>() with singleton { NYTimesRepositoryImpl(instance(), instance()) }
        bind() from provider { NewsViewModelFactory(instance()) }
    }

//    override fun onCreate() {
//        super.onCreate()
//        //AndroidThreeTen.init(this)
//    }
}