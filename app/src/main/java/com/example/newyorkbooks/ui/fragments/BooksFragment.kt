package com.example.newyorkbooks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.newyorkbooks.R
import com.example.newyorkbooks.databinding.FragmentBooksBinding
import com.example.newyorkbooks.ui.adapter.BooksAdapter

import com.example.newyorkbooks.viewModel.BooksViewModel
import kotlinx.android.synthetic.main.fragment_books.*
import org.koin.android.viewmodel.ext.android.viewModel


class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding

    private val viewModel : BooksViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_books, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_main.setTitle(R.string.books_title)

        viewModel.listBooksNetwork()

        viewModel.loadLiveData.observe(this.viewLifecycleOwner, Observer {
            binding.swipeContainer.isRefreshing= it
        })

        viewModel.messageToastLiveData.observe(this.viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.successLiveData.observe(
            this.viewLifecycleOwner,
            Observer {listBooks->
                val adapter = BooksAdapter(listBooks) {
                    val bundle = bundleOf(
                        FavoriteDetailsFragment.BOOK to it
                    )
                    view.findNavController()
                        .navigate(R.id.action_booksActivity_to_favoriteDetails, bundle)
                }
                recycler_books.adapter = adapter
            })

        binding.swipeContainer.setOnRefreshListener {
            viewModel.listBooksNetwork()
        }
    }


}