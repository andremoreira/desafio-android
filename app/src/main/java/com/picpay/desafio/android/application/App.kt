package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(arrayListOf(viewModelModule, repositoryModule))
        }
    }
}