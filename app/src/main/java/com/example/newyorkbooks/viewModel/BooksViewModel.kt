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

        disposables.add(repository.listBooksNetwork().subscribe { res, error ->
            if (error == null) {
                deleteAllBookLocal {
                    disposables.add(repository.insertListBookLocal(res).subscribe())
                }
                successLiveData.value = res
            } else {
                messageToastLiveData.value = context.getString(R.string.error_get_books_network)
                listBooksLocal()
            }
            loadLiveData.value = false
        })
    }


    private fun listBooksLocal() {
        disposables.add(repository.listBooksLocal().subscribe { res, error ->
            if (res.isNotEmpty()) {
                successLiveData.value = res
            } else {
                messageToastLiveData.value = context.getString(R.string.error_get_books_local)
            }
        })
    }

    private fun deleteAllBookLocal(eventSuccess: () -> Unit) {
        disposables.add(repository.deleteListBookLocal().subscribe() { _, error ->
            if (error == null) {
                eventSuccess.invoke()
            }
        })
    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}
