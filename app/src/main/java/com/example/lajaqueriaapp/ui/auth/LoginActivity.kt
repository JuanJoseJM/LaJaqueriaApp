package com.example.lajaqueriaapp.ui.auth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.data.model.LoginRequest
import com.example.lajaqueriaapp.network.ApiClient
import com.example.lajaqueriaapp.network.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var api: AuthApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput = findViewById(R.id.editTextEmail)
        passwordInput = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        api = ApiClient.retrofit.create(AuthApi::class.java)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        val request = LoginRequest(email, password)

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.login(request)
                }

                if (response.isSuccessful) {
                    val token = response.body()?.accesscode ?: ""

                    val prefs = getSharedPreferences("lajaqueria_prefs", Context.MODE_PRIVATE)
                    prefs.edit().putString("access_token", token).apply()

                    Toast.makeText(this@LoginActivity, "Login exitoso", Toast.LENGTH_SHORT).show()
                    Log.d("LOGIN", "Token: $token")
                } else {
                    Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity, "Error de red: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("LOGIN", "Excepción: ${e.message}")
            }
        }
    }
}
