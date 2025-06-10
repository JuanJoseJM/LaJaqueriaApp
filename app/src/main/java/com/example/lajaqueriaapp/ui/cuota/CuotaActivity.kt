package com.example.lajaqueriaapp.ui.cuota

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.ui.adapters.CuotaAdapter
import com.example.lajaqueriaapp.viewmodel.CuotaViewModel

class CuotaActivity : AppCompatActivity() {

    private val viewModel: CuotaViewModel by viewModels()
    private lateinit var cuotaAdapter: CuotaAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuota)

        recyclerView = findViewById(R.id.rvCuotas)
        progressBar = findViewById(R.id.progressBar)

        setupRecyclerView()

        progressBar.visibility = View.VISIBLE
        viewModel.cargarCuotas()

        viewModel.cuotas.observe(this) {
            cuotaAdapter.submitList(it)
            progressBar.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            progressBar.visibility = View.GONE
            it?.let { msg ->
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        cuotaAdapter = CuotaAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cuotaAdapter
    }
}