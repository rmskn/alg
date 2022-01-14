package com.example.shop.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileItem(
    val itemName: String
): Parcelable