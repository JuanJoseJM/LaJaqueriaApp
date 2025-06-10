package com.example.lajaqueriaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lajaqueriaapp.data.model.Cuota
import com.example.lajaqueriaapp.network.ApiClient
import kotlinx.coroutines.launch

class CuotaViewModel : ViewModel() {

    private val _cuotas = MutableLiveData<List<Cuota>>()
    val cuotas: LiveData<List<Cuota>> = _cuotas

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun cargarCuotas() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.obtenerCuotas()
                _cuotas.postValue(response)
            } catch (e: Exception) {
                _error.postValue("No se pudieron cargar las cuotas.")
            }
        }
    }
}
