package com.example.lajaqueriaapp.data.repository

import com.example.lajaqueriaapp.data.model.Socio
import kotlinx.coroutines.flow.Flow

interface SocioRepository {
    suspend fun getSocioById(id: Long): Socio?
    suspend fun getAllSocios(): List<Socio>
    suspend fun createSocio(socio: Socio): Socio
    suspend fun updateSocio(socio: Socio): Socio
    suspend fun deleteSocio(id: Long)
}

