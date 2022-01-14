package com.example.shop.network

import com.example.shop.domain.model.Product
import retrofit2.http.GET

interface RestApi {

    @GET("products")
    suspend fun loadProducts(): List<Product>

    @GET("cart")
    suspend fun loadCart(): List<Product>
}