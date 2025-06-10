package com.example.lajaqueriaapp

import android.app.Application
import android.content.Context

/**
 * Clase de aplicación para acceder al contexto global de forma segura.
 */
class LaJaqueriaApp : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: LaJaqueriaApp

        /**
         * Contexto global accesible desde cualquier parte de la app.
         * Útil para obtener SharedPreferences o recursos sin una Activity.
         */
        val context: Context
            get() = instance.applicationContext
    }
}
