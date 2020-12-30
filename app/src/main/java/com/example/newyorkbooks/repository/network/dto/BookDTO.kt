package com.example.newyorkbooks.repository.network.dto

import com.google.gson.annotations.SerializedName

data class BookDTO(
    val results: List<Results>
)


data class Results(
    @SerializedName(value = "book_details")
    val bookDetails: List<Details>
)

data class Details(
    val title: String,
    val author: String
)
