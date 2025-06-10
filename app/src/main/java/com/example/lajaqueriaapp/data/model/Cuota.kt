package com.example.lajaqueriaapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Cuota(
    val id: Long,
    val socioId: Long,
    val fecha: String,
    val monto: Double
)