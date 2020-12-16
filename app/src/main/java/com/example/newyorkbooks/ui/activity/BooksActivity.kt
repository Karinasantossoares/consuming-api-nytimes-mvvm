package com.example.newyorkbooks.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newyorkbooks.R
import com.example.newyorkbooks.ui.adapter.BooksAdapter
import com.example.newyorkbooks.viewModel.BooksViewModel
import kotlinx.android.synthetic.main.activity_main.*

class BooksActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application)
        ).get(BooksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar_main.title = getString(R.string.books_title)
        setSupportActionBar(toolbar_main)

        viewModel.getBooks()

        viewModel.loadLiveData.observe(this , Observer {
            pb_load.isVisible = it
        })

        viewModel.messageToastLiveData.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })

        viewModel.successLiveData.observe(this, Observer {
            val adapterSuccess = BooksAdapter(it)
            recycler_books.adapter = adapterSuccess
        })

    }
}