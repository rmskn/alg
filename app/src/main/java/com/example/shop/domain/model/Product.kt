package com.example.shop.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["title", "description", "price", "photoUrl"])
data class Product(
    @ColumnInfo val title: String,
    @ColumnInfo val description: String,
    @ColumnInfo val price: String,
    @ColumnInfo val photoUrl: String
): Parcelable