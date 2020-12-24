package com.example.newyorkbooks.repository

import com.example.newyorkbooks.data.Book
import com.example.newyorkbooks.repository.dto.BookDTO
import com.example.newyorkbooks.repository.retrofit.RetrofitConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BooksRepository {
    private val retrofitConfig by lazy { RetrofitConfig() }
    private val service by lazy { retrofitConfig.booksService }

    fun listarLivros() = service.listarLivros().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).map {
        it.results.map{
            Book(it.bookDetails[0].title, it.bookDetails[0].author)
        }
    }
}