package com.example.newyorkbooks.di.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newyorkbooks.repository.local.BookDao
import com.example.newyorkbooks.repository.local.BookEntity


@Database(entities = [BookEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao


    companion object {
        fun instance(context: Context) =
            Room.databaseBuilder(context, AppDataBase::class.java, "AppDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}