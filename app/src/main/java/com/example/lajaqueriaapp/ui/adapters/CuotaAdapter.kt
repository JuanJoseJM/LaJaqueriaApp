package com.example.lajaqueriaapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lajaqueriaapp.R
import com.example.lajaqueriaapp.data.model.Cuota

/**
 * Adaptador para mostrar la lista de cuotas en un RecyclerView.
 */
class CuotaAdapter : RecyclerView.Adapter<CuotaAdapter.CuotaViewHolder>() {

    private var listaCuotas: List<Cuota> = emptyList()

    /**
     * Actualiza la lista de cuotas y notifica cambios.
     */
    fun submitList(nuevaLista: List<Cuota>) {
        listaCuotas = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cuota, parent, false)
        return CuotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuotaViewHolder, position: Int) {
        val cuota = listaCuotas[position]
        holder.tvFecha.text = "Fecha: ${cuota.fecha}"
        holder.tvMonto.text = "Monto: $${cuota.monto}"
    }

    override fun getItemCount(): Int = listaCuotas.size

    /**
     * ViewHolder para los ítems de cuota.
     */
    class CuotaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val tvMonto: TextView = view.findViewById(R.id.tvMonto)
    }
}
