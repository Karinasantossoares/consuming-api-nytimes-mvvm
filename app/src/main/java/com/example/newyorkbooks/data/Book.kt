package com.example.newyorkbooks.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book (
    val title : String,
    val author : String
):Parcelable