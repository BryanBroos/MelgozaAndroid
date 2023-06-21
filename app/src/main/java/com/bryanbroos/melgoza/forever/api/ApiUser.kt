package com.bryanbroos.melgoza.forever.api
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
interface ApiUser {

    @POST(value = "user")
    fun postLogin(@Body userInfo: userInfo):
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

    data class userInfo(
        @SerializedName("name") val name: String,
        @SerializedName("paternalSurname") val paternalSurname: String,
        @SerializedName("maternalSurname") val maternalSurname: String,
        @SerializedName("username") val username: String,
        @SerializedName("password") val password: String,

    )

}


