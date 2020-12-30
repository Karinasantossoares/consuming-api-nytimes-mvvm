package com.example.newyorkbooks

import android.app.Application
import com.example.newyorkbooks.di.persistenceModule
import com.example.newyorkbooks.di.repositoryModule
import com.example.newyorkbooks.di.serviceModule
import com.example.newyorkbooks.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(viewModelModule, repositoryModule, serviceModule,persistenceModule)
        }
    }
}