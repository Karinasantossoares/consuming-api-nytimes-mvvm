package com.example.newyorkbooks.service

import com.example.newyorkbooks.repository.dto.BookDTO
import io.reactivex.Single
import retrofit2.http.*

interface BooksService {

    @GET("lists.json")
    fun listarLivros(
        @Query("api-key")  apiKey :String = "OvLFiMrbuUSoR3bJsO5GUfGtyA6p1JTy",
        @Query("list") list: String = "hardcover-fiction"
    ): Single<BookDTO>

}