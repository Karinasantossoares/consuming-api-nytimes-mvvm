package com.example.newyorkbooks.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newyorkbooks.data.Book
import com.example.newyorkbooks.repository.BooksRepository
import com.example.newyorkbooks.repository.dto.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {
    val repository by lazy { BooksRepository() }
    val loadLiveData = MutableLiveData<Boolean>()
    val successLiveData = MutableLiveData<List<Book>>()
    val messageToastLiveData = MutableLiveData<String>()

    fun getBooks() {
        loadLiveData.value = true
        repository.getBooks().enqueue(object : Callback<BookResponse> {
            override fun onResponse(
                call: Call<BookResponse>,
                bookResponse: Response<BookResponse>
            ) {
                loadLiveData.value = false
                if (bookResponse.isSuccessful) {
                    val listBooksConverted = bookResponse.body()?.results?.map {
                        Book(it.bookDetails[0].title, it.bookDetails[0].author)
                    }
                    successLiveData.value = listBooksConverted
                } else {
                    messageToastLiveData.value = bookResponse.message()
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                loadLiveData.value = false
                messageToastLiveData.value = t.message
            }
        })
    }

}