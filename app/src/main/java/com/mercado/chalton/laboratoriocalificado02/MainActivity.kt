package com.mercado.chalton.laboratoriocalificado02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Cambia el layout a activity_main.xml

        val ejercicio1Button = findViewById<Button>(R.id.ejericio1Button)
        val ejercicio2Button = findViewById<Button>(R.id.ejericio2Button)

        ejercicio1Button.setOnClickListener {
            // Redirigir a la actividad del ejercicio 1
            val intent = Intent(this, Ejercicio01Activity::class.java)
            startActivity(intent)
        }

        ejercicio2Button.setOnClickListener {
            // Redirigir a la actividad del ejercicio 2
            val intent = Intent(this, Ejercicio02Activity::class.java)
            startActivity(intent)
        }
    }
}