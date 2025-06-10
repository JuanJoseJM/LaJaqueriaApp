package com.example.lajaqueriaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lajaqueriaapp.ui.auth.LoginActivity
import com.example.lajaqueriaapp.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("lajaqueria_prefs", MODE_PRIVATE)
        val token = prefs.getString("access_token", null)

        // Redirigir según presencia del token
        val intent = if (token != null) {
            Intent(this, HomeActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish() // Evita volver a esta pantalla
    }
}
