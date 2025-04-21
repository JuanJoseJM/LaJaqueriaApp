package com.example.lajaqueriaapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Evento(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val ubicacion: String,
    val asistentes: List<Socio>
)
