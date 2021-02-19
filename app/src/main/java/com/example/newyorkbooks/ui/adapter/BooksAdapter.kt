package com.example.newyorkbooks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newyorkbooks.R
import com.example.newyorkbooks.data.Book

class   BooksAdapter(private val books: List<Book>, val onClick: (Book) -> Unit) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(itemView)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    inner class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val author = itemView.findViewById<TextView>(R.id.tv_author)
        private val favorite = itemView.findViewById<TextView>(R.id.tv_favorite)

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author
            favorite.setOnClickListener {
                onClick.invoke(book)
            }
        }
    }

}
