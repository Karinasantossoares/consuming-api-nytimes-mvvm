package com.example.newyorkbooks.repository


import com.example.newyorkbooks.data.Book
import com.example.newyorkbooks.repository.local.BookDao
import com.example.newyorkbooks.repository.local.BookEntity
import com.example.newyorkbooks.service.BooksService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BooksRepository(
    private val service: BooksService,
    private val bookDao: BookDao
) {


    fun listBooksNetwork() = service.listarLivros().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).map { bookDto ->
            bookDto.results.map {
                Book(it.bookDetails[0].title, it.bookDetails[0].author)
            }
        }

    fun listBooksLocal() = bookDao.listBooks().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).map { listBookEntity ->
            listBookEntity.map {
                Book(it.title, it.author)
            }
        }

    fun insertListBookLocal(listBook: List<Book>) =
        Single.fromCallable {
            bookDao.addAll(listBook.map {
                BookEntity(title = it.title, author = it.author)
            })
        }.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteListBookLocal() =
        Single.fromCallable {
            bookDao.deleteAll()
        }.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
}

