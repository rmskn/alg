package com.example.shop.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: ProductsDatabase? = null

    fun provideDatabase(context: Context): ProductsDatabase {
        return db ?: Room.databaseBuilder(context.applicationContext, ProductsDatabase::class.java, "products_database").build()
            .also { db = it }
    }
}