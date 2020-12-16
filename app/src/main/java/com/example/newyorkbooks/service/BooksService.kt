package com.example.newyorkbooks.service

import com.example.newyorkbooks.repository.dto.BookResponse
import retrofit2.Call
import retrofit2.http.*

interface BooksService {

    @GET("lists.json")
    fun listarLivros(
        @Query("api-key")  apiKey :String = "OvLFiMrbuUSoR3bJsO5GUfGtyA6p1JTy",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookResponse>
}