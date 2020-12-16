package com.example.newyorkbooks.repository

import com.example.newyorkbooks.repository.retrofit.RetrofitConfig

class BooksRepository {
    private val retrofitConfig by lazy { RetrofitConfig() }
    private val service by lazy { retrofitConfig.booksService }

    fun getBooks() = service.listarLivros()
}