package com.example.lajaqueriaapp.data.repository

import com.example.lajaqueriaapp.data.api.SocioApi
import com.example.lajaqueriaapp.data.model.Socio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

class SocioRepositoryImpl(private val socioApi: SocioApi) : SocioRepository {

    // Obtener un socio por su ID desde la API
    override suspend fun getSocioById(id: Long): Socio? {
        return try {
            socioApi.getSocioById(id)
        } catch (e: Exception) {
            null
        }
    }

    // Obtener todos los socios
    override suspend fun getAllSocios(): List<Socio> {
        return try {
            socioApi.getAllSocios()
        } catch (e: Exception) {
            emptyList()  // Devolver lista vacía si hay un error
        }
    }

    // Crear un nuevo socio
    override suspend fun createSocio(socio: Socio): Socio {
        return try {
            socioApi.createSocio(socio)
        } catch (e: Exception) {
            throw e // Lanza una excepción en caso de error
        }
    }

    // Actualizar un socio
    override suspend fun updateSocio(socio: Socio): Socio {
        return try {
            socioApi.updateSocio(socio)
        } catch (e: Exception) {
            throw e // Lanza una excepción en caso de error
        }
    }

    // Eliminar un socio
    override suspend fun deleteSocio(id: Long) {
        try {
            socioApi.deleteSocio(id)
        } catch (e: Exception) {
            throw e // Lanza una excepción si no se puede eliminar
        }
    }
}
