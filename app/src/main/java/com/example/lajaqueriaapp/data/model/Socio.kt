package com.example.lajaqueriaapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Socio(
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val telefono: String,
    val fechaNacimiento: String,
    val esAdministrador: Boolean // Indica si el socio es admin o no
)
