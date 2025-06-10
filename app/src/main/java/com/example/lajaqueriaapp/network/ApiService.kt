package com.example.lajaqueriaapp.network

import com.example.lajaqueriaapp.data.model.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/usuarios/me")
    suspend fun obtenerPerfil(): Socio

    @GET("/cuotas")
    suspend fun obtenerCuotas(): List<Cuota>

    @GET("/eventos")
    suspend fun obtenerEventos(): List<Evento>

    @POST("/eventos/{id}/asistir")
    suspend fun inscribirse(@Path("id") eventoId: Long)

    @POST("/api/domotica/{accion}")
    suspend fun controlarDomotica(@Path("accion") accion: String): DomoticaResponse
}
