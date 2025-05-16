package com.example.lajaqueriaapp

import android.app.Application
import android.content.Context

class LaJaqueriaApp : Application() {
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: LaJaqueriaApp
        val context: Context get() = instance.applicationContext
    }
}
