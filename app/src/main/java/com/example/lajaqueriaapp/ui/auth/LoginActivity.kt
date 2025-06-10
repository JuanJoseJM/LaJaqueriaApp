package com.example.lajaqueriaapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.ui.home.HomeActivity
import com.example.lajaqueriaapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput = findViewById(R.id.editTextEmail)
        passwordInput = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        progressBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, password)
            }
        }

        viewModel.loading.observe(this) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loginSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        viewModel.error.observe(this) {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }
        }
    }
}