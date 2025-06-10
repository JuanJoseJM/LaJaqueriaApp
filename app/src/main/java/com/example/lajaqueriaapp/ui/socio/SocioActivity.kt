package com.example.lajaqueriaapp.ui.socio

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.ui.auth.LoginActivity
import com.example.lajaqueriaapp.viewmodel.SocioViewModel

class SocioActivity : AppCompatActivity() {

    private val viewModel: SocioViewModel by viewModels()

    private lateinit var tvNombre: TextView
    private lateinit var tvApellidos: TextView
    private lateinit var tvEmail: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socio)

        tvNombre = findViewById(R.id.tvNombre)
        tvApellidos = findViewById(R.id.tvApellidos)
        tvEmail = findViewById(R.id.tvEmail)
        btnLogout = findViewById(R.id.btnLogout)

        viewModel.cargarSocio()

        viewModel.socio.observe(this) { socio ->
            tvNombre.text = "Nombre: ${socio.nombre}"
            tvApellidos.text = "Apellidos: ${socio.apellidos}"
            tvEmail.text = "Correo: ${socio.email}"
        }

        viewModel.error.observe(this) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        btnLogout.setOnClickListener {
            confirmarLogout()
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
