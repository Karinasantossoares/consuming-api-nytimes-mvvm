package com.example.newyorkbooks.repository

import com.example.newyorkbooks.data.Book
import com.example.newyorkbooks.repository.dto.BookDTO
import com.example.newyorkbooks.service.BooksService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BooksRepository(private val service: BooksService) {


    fun listarLivros() = service.listarLivros().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).map {bookDto->
            bookDto.results.map {
                Book(it.bookDetails[0].title, it.bookDetails[0].author)
            }
        }
}