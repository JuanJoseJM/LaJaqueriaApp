package com.example.lajaqueriaapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.data.model.Evento

class EventoAdapter(private val onInscribirseClick: (Long) -> Unit) :
    RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    private var listaEventos: List<Evento> = emptyList()

    fun submitList(nuevaLista: List<Evento>) {
        listaEventos = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = listaEventos[position]
        holder.tvNombre.text = evento.nombre
        holder.tvFecha.text = evento.fecha
        holder.tvDescripcion.text = evento.descripcion
        holder.btnInscribirse.setOnClickListener {
            onInscribirseClick(evento.id)
        }
    }

    override fun getItemCount(): Int = listaEventos.size

    class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreEvento)
        val tvFecha: TextView = view.findViewById(R.id.tvFechaEvento)
        val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcionEvento)
        val btnInscribirse: Button = view.findViewById(R.id.btnInscribirse)
    }
}
