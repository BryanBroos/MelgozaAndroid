package com.bryanbroos.melgoza.forever.api

import com.bryanbroos.melgoza.forever.model.Product
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiProduct {
    @GET(value = "products")
    fun getProduct(@Body productInfo: productInfo):
            Call<Product>

    @GET(value = "products")
    fun getAllProducts(@Header("Authorization") token: String):
            Call<List<Product>>

    companion object Factory{
        private const val BASE_URL = "http://10.0.2.2:8080/api/"
        fun create(): ApiProduct{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiProduct::class.java)
        }
    }

    data class productInfo(
        @SerializedName("name") val name: String,
        @SerializedName("category") val category: String,
        @SerializedName("price") val price: Int,
        @SerializedName("description") val description: String,
        @SerializedName("stock") val available: Int,
        @SerializedName("size") val size: Float,
        @SerializedName("target") val target: Int,
        @SerializedName("brandid") val brandid: Int,

        )




}