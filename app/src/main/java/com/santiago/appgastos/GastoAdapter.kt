package com.santiago.appgastos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GastoAdapter(private val lista: List<MainActivity.Gasto>) : RecyclerView.Adapter<GastoAdapter.GastoViewHolder>() {

    class GastoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvConcepto = view.findViewById<TextView>(R.id.tvConceptoItem)
        val tvImporte = view.findViewById<TextView>(R.id.tvImporteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gasto, parent, false)
        return GastoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GastoViewHolder, position: Int) {
        val gasto = lista[position]
        holder.tvConcepto.text = gasto.concepto
        holder.tvImporte.text = "${gasto.importe} €"
    }

    override fun getItemCount() = lista.size
}