package com.santiago.appgastos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    // 1. Definimos las variables arriba para que TODA la clase las vea
    private val listaDeGastos = mutableListOf<Gasto>()
    private lateinit var adaptador: GastoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Configuramos el RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rvGastos)
        rv.layoutManager = LinearLayoutManager(this)
        cargarDatos()
        adaptador = GastoAdapter(listaDeGastos)
        rv.adapter = adaptador

        val boton = findViewById<Button>(R.id.btnAnadirGasto)

        boton.setOnClickListener {
            val importe = findViewById<EditText>(R.id.etImporte).text.toString()
            val concepto = findViewById<EditText>(R.id.etConcepto).text.toString()

            if (importe.isNotEmpty() && concepto.isNotEmpty()) {
                // Añadimos el gasto
                listaDeGastos.add(Gasto(concepto, importe))
                guardarDatos()

                // Notificamos al adaptador
                adaptador.notifyDataSetChanged()

                // LLAMAMOS A LA FUNCIÓN DE SUMAR
                actualizarTotal()

                // Limpiamos los campos
                findViewById<EditText>(R.id.etImporte).text.clear()
                findViewById<EditText>(R.id.etConcepto).text.clear()
            }
        }
    }

    // 3. LA FUNCIÓN PARA SUMAR (Fuera del onCreate, pero dentro de la clase)
    private fun actualizarTotal() {
        val suma = listaDeGastos.sumOf { it.importe.toDoubleOrNull() ?: 0.0 }
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        // El chivato:
        Toast.makeText(this, "Calculando suma: $suma", Toast.LENGTH_SHORT).show()

        tvTotal.text = "Total: ${String.format("%.2f", suma)} €"
    }

    // Funcion para guardar

    private fun guardarDatos(){
        val sharedPreferences = getSharedPreferences("mis_gastos", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()

        // Convertir lista a texto (JSON)
        val json = gson.toJson(listaDeGastos)

        editor.putString("lista_gastos",json)
        editor.apply() // Guardado

    }

    private fun cargarDatos() {
        val sharedPreferences = getSharedPreferences("mis_gastos", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("lista_gastos", null)

        if (json != null) {
            // Convertimos el texto de nuevo a una lista de objetos Gasto
            val type = object : TypeToken<MutableList<Gasto>>() {}.type
            val listaGuardada: MutableList<Gasto> = gson.fromJson(json, type)

            listaDeGastos.clear()
            listaDeGastos.addAll(listaGuardada)
        }

    }

}