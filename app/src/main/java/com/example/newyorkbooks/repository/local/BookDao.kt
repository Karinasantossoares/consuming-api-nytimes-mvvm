package com.example.newyorkbooks.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import retrofit2.http.DELETE

@Dao
interface BookDao {
    @Insert
    fun addAll(listBookEntity: List<BookEntity>)

    @Query("DELETE FROM `book-table`")
    fun deleteAll()

    @Query("SELECT *FROM `book-table`")
    fun listBooks(): Single<List<BookEntity>>
}