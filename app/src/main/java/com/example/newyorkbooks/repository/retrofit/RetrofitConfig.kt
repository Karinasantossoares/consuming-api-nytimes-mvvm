package com.example.newyorkbooks.repository.retrofit

import com.example.newyorkbooks.service.BooksService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = (HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val booksService = initRetrofit().create(BooksService::class.java)
}