package com.bryanbroos.melgoza.forever.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.bryanbroos.melgoza.R
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.ui.AppBarConfiguration
import com.bryanbroos.melgoza.databinding.ActivityLoginBinding
import com.bryanbroos.melgoza.forever.api.ApiLogin
import com.bryanbroos.melgoza.forever.api.InicioRespuesta
import retrofit2.Call
import retrofit2.Response
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper.get
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper.set

class Signup : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private val apiLogin: ApiLogin by lazy {
        ApiLogin.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val btnSignUp = findViewById<AppCompatButton>(R.id.btnSignUp)
        btnSignUp.setOnClickListener{
            val intent = Intent(this, com.bryanbroos.melgoza.forever.ui.login::class.java)
            Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }



    }


}