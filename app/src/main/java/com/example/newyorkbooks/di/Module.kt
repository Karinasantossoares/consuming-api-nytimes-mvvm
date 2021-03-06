package com.example.newyorkbooks.di

import com.example.newyorkbooks.di.database.AppDataBase
import com.example.newyorkbooks.repository.BooksRepository
import com.example.newyorkbooks.di.retrofit.initRetrofit
import com.example.newyorkbooks.repository.local.BookDao
import com.example.newyorkbooks.service.BooksService
import com.example.newyorkbooks.viewModel.BooksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { BooksViewModel(androidContext(),get()) }
}

val repositoryModule = module {
    single { BooksRepository(get(),get()) }
}

val persistenceModule = module {
    single { AppDataBase.instance(androidContext()) }
    single {get<AppDataBase>().bookDao()  }

}

val serviceModule = module {
    single { initRetrofit() }
    single { get<Retrofit>().create(BooksService::class.java) }
}