package com.example.lajaqueriaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lajaqueriaapp.data.model.Socio
import com.example.lajaqueriaapp.data.repository.SocioRepository
import kotlinx.coroutines.launch

class SocioViewModel(private val socioRepository: SocioRepository) : ViewModel() {

    // Obtener todos los socios
    fun getAllSocios() {
        viewModelScope.launch {
            val socios = socioRepository.getAllSocios()
            // Lógica para actualizar la UI con la lista de socios
        }
    }

    // Obtener un socio por ID
    fun getSocioById(id: Long) {
        viewModelScope.launch {
            val socio = socioRepository.getSocioById(id)
            // Lógica para actualizar la UI con el socio
        }
    }

    // Crear un nuevo socio
    fun createSocio(socio: Socio) {
        viewModelScope.launch {
            val createdSocio = socioRepository.createSocio(socio)
            // Lógica para manejar el socio creado
        }
    }

    // Actualizar un socio
    fun updateSocio(socio: Socio) {
        viewModelScope.launch {
            val updatedSocio = socioRepository.updateSocio(socio)
            // Lógica para manejar el socio actualizado
        }
    }

    // Eliminar un socio
    fun deleteSocio(id: Long) {
        viewModelScope.launch {
            socioRepository.deleteSocio(id)
            // Lógica para manejar el socio eliminado
        }
    }
}
