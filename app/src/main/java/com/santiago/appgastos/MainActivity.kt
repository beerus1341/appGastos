package com.santiago.appgastos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    data class Gasto(val concepto : String, val importe : String)

    //Crear lista para guardar gastos
    private val listaDeGastos = mutableListOf<Gasto>()
    private lateinit var adaptador: GastoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Dentro del onCreate, configura el RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rvGastos)
        rv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador = GastoAdapter(listaDeGastos)
        rv.adapter = adaptador

        // buscar el boton por le id
        val boton = findViewById<Button>(R.id.btnAnadirGasto)

        // accion a realizar cuando le de al boton
        boton.setOnClickListener {
            val importe = findViewById<EditText>(R.id.etImporte).text.toString()
            val concepto = findViewById<EditText>(R.id.etConcepto).text.toString()

            if (importe.isNotEmpty() && concepto.isNotEmpty()) {
                // 1. Creamos el gasto
                val nuevoGasto = Gasto(concepto, importe)

                // 2. LO AÑADIMOS A LA LISTA
                listaDeGastos.add(nuevoGasto)

                // 3. AVISAMOS AL ADAPTADOR (Crucial para que aparezca en pantalla)
                adaptador.notifyDataSetChanged()

                // 4. Limpiamos los cuadros para el siguiente
                findViewById<EditText>(R.id.etImporte).text.clear()
                findViewById<EditText>(R.id.etConcepto).text.clear()

                Toast.makeText(this, "Añadido: $concepto", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Rellena los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

