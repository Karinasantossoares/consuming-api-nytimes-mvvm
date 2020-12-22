package com.example.newyorkbooks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.newyorkbooks.R
import com.example.newyorkbooks.data.Book
import com.example.newyorkbooks.databinding.FragmentFavoriteDetailsBinding

class FavoriteDetailsFragment : Fragment() {
    private lateinit var binding:FragmentFavoriteDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_details, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book =  arguments?.getParcelable<Book>(BOOK)
        binding.tvDetailsAuthor.text = book?.author
        binding.tvDetailsTitle.text = book?.title
    }

    companion object {
        const val BOOK = "BOOK"
    }
}