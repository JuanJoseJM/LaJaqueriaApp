package com.example.lajaqueriaapp.data.repository

import com.example.lajaqueriaapp.data.api.SocioApi
import com.example.lajaqueriaapp.data.model.Socio

/**
 * Implementación del repositorio de socios.
 * Usa una API para obtener, listar, crear, actualizar y eliminar socios.
 */
class SocioRepositoryImpl(private val socioApi: SocioApi) : SocioRepository {

    override suspend fun getSocioById(id: Long): Socio? {
        return try {
            socioApi.getSocioById(id)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllSocios(): List<Socio> {
        return try {
            socioApi.getAllSocios()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun createSocio(socio: Socio): Socio {
        return socioApi.createSocio(socio)
    }

    override suspend fun updateSocio(socio: Socio): Socio {
        return socioApi.updateSocio(socio.id, socio)
    }

    override suspend fun deleteSocio(id: Long) {
        socioApi.deleteSocio(id)
    }
}
