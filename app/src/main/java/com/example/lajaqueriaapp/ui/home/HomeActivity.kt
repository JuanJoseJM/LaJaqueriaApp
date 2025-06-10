package com.example.lajaqueriaapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.network.ApiClient
import com.example.lajaqueriaapp.ui.auth.LoginActivity
import com.example.lajaqueriaapp.ui.cuota.CuotaActivity
import com.example.lajaqueriaapp.ui.domotica.DomoticaActivity
import com.example.lajaqueriaapp.ui.evento.EventoActivity
import com.example.lajaqueriaapp.ui.socio.SocioActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private lateinit var btnDomotica: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btnPerfil).setOnClickListener {
            startActivity(Intent(this, SocioActivity::class.java))
        }

        findViewById<Button>(R.id.btnCuotas).setOnClickListener {
            startActivity(Intent(this, CuotaActivity::class.java))
        }

        findViewById<Button>(R.id.btnEventos).setOnClickListener {
            startActivity(Intent(this, EventoActivity::class.java))
        }

        btnDomotica = findViewById(R.id.btnDomotica)
        btnDomotica.setOnClickListener {
            startActivity(Intent(this, DomoticaActivity::class.java))
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            confirmarLogout()
        }

        verificarPermisoDomotica()
    }

    private fun verificarPermisoDomotica() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usuario = ApiClient.apiService.obtenerPerfil()
                withContext(Dispatchers.Main) {
                    btnDomotica.visibility = if (usuario.rol == "admin") View.VISIBLE else View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    btnDomotica.visibility = View.GONE
                    Toast.makeText(this@HomeActivity, "No se pudo verificar permisos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun confirmarLogout() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro de que deseas cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->
                val prefs = getSharedPreferences("lajaqueria_prefs", MODE_PRIVATE)
                prefs.edit().remove("access_token").apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}