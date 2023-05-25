package com.bryanbroos.melgoza.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.ui.AppBarConfiguration
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        val etUsuario = findViewById<AppCompatEditText>(R.id.etUsuario)
        val signup = findViewById<AppCompatTextView>(R.id.signup)


        btnLogin.setOnClickListener{
            val name = etUsuario.text.toString()

            if(name.isNotEmpty()){
                val intent = Intent(this, com.bryanbroos.melgoza.ui.menu.MainMenu::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }
        }
        signup.setOnClickListener{
        val intent = Intent(this, com.bryanbroos.melgoza.ui.signup.signup::class.java)
        startActivity(intent)
        }
        }
}