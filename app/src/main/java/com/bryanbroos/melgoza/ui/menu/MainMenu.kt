package com.bryanbroos.melgoza.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.ui.menu.userprofile.Profile

class MainMenu : AppCompatActivity() {

    private lateinit var viewUser:CardView
    private lateinit var viewShopping:CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val txtUsername = findViewById<TextView>(R.id.txtUsername)
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        txtUsername.text = "Bienvenido $name"
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        viewUser = findViewById(R.id.viewUser)
        viewShopping = findViewById(R.id.viewShopping)
    }
    private fun initListeners() {
     viewUser.setOnClickListener{

             val intent = Intent(this, Profile::class.java)
            startActivity(intent)

     }
      viewShopping.setOnClickListener {
          val intent = Intent(this, com.bryanbroos.melgoza.ui.menu.Shopping::class.java)
          startActivity(intent)
      }
    }
}