package com.example.newyorkbooks.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newyorkbooks.R
import com.example.newyorkbooks.data.Book
import com.example.newyorkbooks.repository.BooksRepository
import io.reactivex.disposables.CompositeDisposable

class BooksViewModel(private val context: Context, private val repository: BooksRepository) :
    ViewModel() {
    var disposables = CompositeDisposable()

    val loadLiveData = MutableLiveData<Boolean>()
    val successLiveData = MutableLiveData<List<Book>>()
    val messageToastLiveData = MutableLiveData<String>()

    fun listBooksNetwork() {
        loadLiveData.value = true
        disposables.add(repository.listBooksNetwork().doOnSuccess { listBooks ->
            deleteAllBookLocal {
                insertListBookLocal(listBooks)
            }
            successLiveData.value = listBooks
        }.doOnError {
            messageToastLiveData.value = context.getString(R.string.error_get_books_network)
        }.doFinally {
            loadLiveData.value = false
        }.subscribe({}, {
            listBooksLocal()
        }))
    }

    private fun listBooksLocal() {
        repository.listBooksLocal()
            .doOnSubscribe {
                disposables.add(it)
            }
            .doOnSuccess {
                if (it.isEmpty()) {
                    messageToastLiveData.value = context.getString(R.string.error_get_books_local)
                } else {
                    successLiveData.value = it
                }
            }
            .doOnError {
                messageToastLiveData.value = context.getString(R.string.error_get_books_local)
            }.subscribe()
    }

    private fun insertListBookLocal(listBook: List<Book>) {
        repository.insertListBookLocal(listBook)
            .doOnSubscribe {
                disposables.add(it)
            }.doOnError {}.subscribe()
    }

    private fun deleteAllBookLocal(eventSuccess: () -> Unit) {
        repository.deleteListBookLocal().doOnSubscribe {
            disposables.add(it)
        }.doOnSuccess {
            eventSuccess.invoke()
        }.doOnError {}.subscribe()
    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}
