package com.example.lajaqueriaapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lajaqueriaapp.data.model.LoginRequest
import com.example.lajaqueriaapp.network.ApiClient
import com.example.lajaqueriaapp.network.AuthApi
import kotlinx.coroutines.launch

/**
 * ViewModel para manejar la lógica de inicio de sesión.
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val context = application.applicationContext
    private val prefs = context.getSharedPreferences("lajaqueria_prefs", Context.MODE_PRIVATE)
    private val api = ApiClient.retrofit.create(AuthApi::class.java)

    /**
     * Ejecuta el proceso de login.
     * @param email Correo del usuario
     * @param password Contraseña del usuario
     */
    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _error.value = "Todos los campos son obligatorios"
            return
        }

        _loading.value = true
        viewModelScope.launch {
            try {
                val response = api.login(LoginRequest(email.trim(), password.trim()))
                val loginResponse = response.body()
                if (response.isSuccessful && loginResponse != null) {
                    prefs.edit().putString("access_token", loginResponse.token).apply()
                    _loginSuccess.postValue(true)
                } else {
                    _error.postValue("Error de autenticación")
                }
            } catch (e: Exception) {
                _error.postValue("Credenciales inválidas o error de red")
            } finally {
                _loading.postValue(false)
            }
        }
    }

    /**
     * Limpia cualquier error actual mostrado.
     */
    fun clearError() {
        _error.value = null
    }
}