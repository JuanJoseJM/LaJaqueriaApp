package com.example.lajaqueriaapp.ui.evento

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.ui.adapters.EventoAdapter
import com.example.lajaqueriaapp.viewmodel.EventoViewModel

/**
 * Actividad para mostrar la lista de eventos e inscribirse.
 */
class EventoActivity : AppCompatActivity() {

    private val viewModel: EventoViewModel by viewModels()
    private lateinit var adapter: EventoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evento)

        val recyclerView = findViewById<RecyclerView>(R.id.rvEventos)

        // Adaptador con callback para inscripción
        adapter = EventoAdapter { eventoId ->
            viewModel.inscribirse(eventoId)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observadores del ViewModel
        viewModel.eventos.observe(this) {
            adapter.submitList(it)
        }

        viewModel.error.observe(this) {
            it?.let { msg -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
        }

        viewModel.exito.observe(this) {
            if (it) Toast.makeText(this, "Inscripción exitosa", Toast.LENGTH_SHORT).show()
        }

        // Cargar eventos desde la API
        viewModel.cargarEventos()
    }
}