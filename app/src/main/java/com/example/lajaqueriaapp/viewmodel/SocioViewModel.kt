package com.example.lajaqueriaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lajaqueriaapp.data.model.Socio
import com.example.lajaqueriaapp.network.ApiClient
import kotlinx.coroutines.launch

class SocioViewModel : ViewModel() {

    private val _socio = MutableLiveData<Socio>()
    val socio: LiveData<Socio> = _socio

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun cargarSocio() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.obtenerPerfil()
                _socio.postValue(response)
            } catch (e: Exception) {
                _error.postValue("Error al cargar el perfil.")
            }
        }
    }
}
