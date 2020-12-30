package com.example.newyorkbooks.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book-table")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val title: String,
    val author: String
)