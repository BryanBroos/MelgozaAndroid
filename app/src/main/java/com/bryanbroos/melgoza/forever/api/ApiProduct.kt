package com.bryanbroos.melgoza.forever.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiProduct {
    @GET(value = "product")
    fun postLogin(@Body productInfo: productInfo):
            Call<InicioRespuesta>

    companion object Factory{
        private const val BASE_URL = "http://10.0.2.2:8080/api/"
        fun create(): ApiLogin{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiLogin::class.java)
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