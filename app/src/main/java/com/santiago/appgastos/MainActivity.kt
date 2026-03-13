package com.santiago.appgastos

import android.os.Bundle
import android.widget.Button
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
            // Mostrar mensaje en pantalla
            Toast.makeText(this,"Boton Pulsado",Toast.LENGTH_SHORT).show()
        }
    }
}