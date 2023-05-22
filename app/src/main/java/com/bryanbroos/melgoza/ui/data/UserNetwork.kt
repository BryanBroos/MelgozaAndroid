package com.bryanbroos.melgoza.ui.data

import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

class UserNetwork {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }
}