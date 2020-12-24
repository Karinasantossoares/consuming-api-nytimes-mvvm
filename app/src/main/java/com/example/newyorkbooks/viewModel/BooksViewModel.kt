package com.example.newyorkbooks.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newyorkbooks.data.Book
import com.example.newyorkbooks.repository.BooksRepository
import io.reactivex.disposables.Disposable

class BooksViewModel : ViewModel() {
    var disposableListBook: Disposable? = null
    val repository by lazy { BooksRepository() }
    val loadLiveData = MutableLiveData<Boolean>()
    val successLiveData = MutableLiveData<List<Book>>()
    val messageToastLiveData = MutableLiveData<String>()

    fun listarLivros() {
        loadLiveData.value = true
        repository.listarLivros()
            .doOnSubscribe {
                disposableListBook = it
            }
            .doOnSuccess {
                successLiveData.value = it
            }
            .doOnError {
                messageToastLiveData.value = it.message
            }
            .doFinally {
                loadLiveData.value = false
            }.subscribe()
    }
    override fun onCleared() {
        super.onCleared()
        disposableListBook?.dispose()
    }

}
