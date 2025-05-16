package com.example.lajaqueriaapp.data.api

import com.example.lajaqueriaapp.data.model.Socio
import retrofit2.http.*

interface SocioApi {

    // Obtener todos los socios
    @GET("socios")
    suspend fun getAllSocios(): List<Socio>

    // Obtener un socio por su ID
    @GET("/socios/{id}")
    suspend fun getSocioById(@Path("id") id: Long): Socio

    // Crear un nuevo socio
    @POST("socios")
    suspend fun createSocio(@Body socio: Socio): Socio

    // Actualizar un socio existente
    @PUT("socios/{id}")
    suspend fun updateSocio(@Path("id") id: Long, @Body socio: Socio): Socio

    // Eliminar un socio
    @DELETE("socios/{id}")
    suspend fun deleteSocio(@Path("id") id: Long)
}