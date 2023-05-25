package com.bryanbroos.melgoza.ui.api

import com.bryanbroos.melgoza.ui.data.model.PeticionInicio
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiLogin {
    @POST("Login")
    suspend fun postInicioSesion(@Body peticion: PeticionInicio): Response<InicioRespuesta>

}