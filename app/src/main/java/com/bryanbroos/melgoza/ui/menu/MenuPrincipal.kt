package com.bryanbroos.melgoza.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bryanbroos.melgoza.R

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        val txtUsername = findViewById<TextView>(R.id.txtUsername)
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        txtUsername.text = "Bienvenido $name"
    }
}