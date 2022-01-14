package com.example.shop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileItem(
    val itemName: String
): Parcelable