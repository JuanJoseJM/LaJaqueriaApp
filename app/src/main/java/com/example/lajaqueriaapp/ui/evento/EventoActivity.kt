package com.example.lajaqueriaapp.ui.evento

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.ui.adapters.EventoAdapter
import com.example.lajaqueriaapp.viewmodel.EventoViewModel
import kotlinx.android.synthetic.main.activity_evento.*

class EventoActivity : AppCompatActivity() {

    private val viewModel: EventoViewModel by viewModels()
    private lateinit var adapter: EventoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evento)

        adapter = EventoAdapter { eventoId ->
            viewModel.inscribirse(eventoId)
        }

        rvEventos.layoutManager = LinearLayoutManager(this)
        rvEventos.adapter = adapter

        viewModel.eventos.observe(this) {
            adapter.submitList(it)
        }

        viewModel.error.observe(this) {
            it?.let { msg -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
        }

        viewModel.exito.observe(this) {
            if (it) Toast.makeText(this, "Inscripción exitosa", Toast.LENGTH_SHORT).show()
        }

        viewModel.cargarEventos()
    }
}
