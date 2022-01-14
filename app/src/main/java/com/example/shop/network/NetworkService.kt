package com.example.shop.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalSerializationApi
object NetworkService {

    private const val BASE_URL = "http://demo1879963.mockable.io/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val restApi = retrofit.create(RestApi::class.java)

    suspend fun loadCatalog() = restApi.loadProducts()

    suspend fun loadCart() = restApi.loadCart()
}