package com.example.lajaqueriaapp.ui.domotica

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DomoticaActivity : AppCompatActivity() {

    private lateinit var estadoTextView: TextView
    private val api = ApiClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domotica)

        val btnEncender = findViewById<Button>(R.id.btnEncender)
        val btnApagar = findViewById<Button>(R.id.btnApagar)
        estadoTextView = findViewById(R.id.textEstado)

        btnEncender.setOnClickListener { controlarDomotica("encender") }
        btnApagar.setOnClickListener { controlarDomotica("apagar") }
    }

    private fun controlarDomotica(accion: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.controlarDomotica(accion)
                withContext(Dispatchers.Main) {
                    estadoTextView.text = "Estado: ${accion.uppercase()}"
                    Toast.makeText(this@DomoticaActivity, response.message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@DomoticaActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
