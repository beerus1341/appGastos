package com.santiago.appgastos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // buscar el boton por le id
        val boton = findViewById<Button>(R.id.btnAnadirGasto)

        // accion a realizar cuando le de al boton
        boton.setOnClickListener{
            // leer lo que el usuario ingreso
            val importe = findViewById<EditText>(R.id.etImporte).text.toString()
            val concepto = findViewById<EditText>(R.id.etConcepto).text.toString()

            // Comprobar que no este vacio
            if (importe.isNotEmpty() && concepto.isNotEmpty()){
                // Mostramos mensaje con los datos
                Toast.makeText(this, "Gastado, $importe en $concepto",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Por favor, rellena todos los campos",Toast.LENGTH_SHORT).show()
            }

        }
    }
}