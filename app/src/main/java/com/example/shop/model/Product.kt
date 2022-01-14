package com.example.shop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val title: String,
    val description: String,
    val price: String,
    val photoUrl: String
): Parcelable