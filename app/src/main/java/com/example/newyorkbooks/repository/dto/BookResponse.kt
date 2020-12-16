package com.example.newyorkbooks.repository.dto

import com.google.gson.annotations.SerializedName

data class BookResponse(
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
