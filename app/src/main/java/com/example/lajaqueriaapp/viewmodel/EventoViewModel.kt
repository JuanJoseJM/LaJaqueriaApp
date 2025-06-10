package com.example.lajaqueriaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lajaqueriaapp.data.model.Evento
import com.example.lajaqueriaapp.network.ApiClient
import kotlinx.coroutines.launch

class EventoViewModel : ViewModel() {

    private val _eventos = MutableLiveData<List<Evento>>()
    val eventos: LiveData<List<Evento>> = _eventos

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _exito = MutableLiveData<Boolean>()
    val exito: LiveData<Boolean> = _exito

    fun cargarEventos() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.obtenerEventos()
                _eventos.postValue(response)
            } catch (e: Exception) {
                _error.postValue("No se pudieron cargar los eventos.")
            }
        }
    }

    fun inscribirse(eventoId: Long) {
        viewModelScope.launch {
            try {
                ApiClient.apiService.inscribirse(eventoId)
                _exito.postValue(true)
            } catch (e: Exception) {
                _error.postValue("Error al inscribirse.")
            }
        }
    }
}
