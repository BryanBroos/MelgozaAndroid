package com.bryanbroos.melgoza.forever.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.ui.AppBarConfiguration
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.databinding.ActivityLoginBinding
import com.bryanbroos.melgoza.forever.api.ApiLogin
import com.bryanbroos.melgoza.forever.api.InicioRespuesta
import retrofit2.Call
import retrofit2.Response
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper.get
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper.set


class login : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private val apiLogin: ApiLogin by lazy {
        ApiLogin.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        val signup = findViewById<AppCompatTextView>(R.id.signup)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["token", ""].contains("."))
            goToMenu()



        btnLogin.setOnClickListener{
            performLogin()
        }
        signup.setOnClickListener{
            val intent = Intent(this, com.bryanbroos.melgoza.forever.ui.Signup::class.java)
            startActivity(intent)
        }

    }
    private fun goToMenu(){
        val i = Intent(this, MainMenu::class.java)
        startActivity(i)
        finish()


    }

    private fun createSessionPreference(token: String){

        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["token"] = token

    }


    private fun performLogin(){
        val etUsuario = findViewById<AppCompatEditText>(R.id.etUsuario).text.toString()
        val etPassword = findViewById<AppCompatEditText>(R.id.etpassword).text.toString()

        val call = apiLogin.postLogin(ApiLogin.userInfo(etUsuario, etPassword) )
        call.enqueue(object : retrofit2.Callback<InicioRespuesta>{
            override fun onResponse(
                call: Call<InicioRespuesta>,
                response: Response<InicioRespuesta>
            ) {
                if(response.isSuccessful){
                    val token = response.body()
                    if (token != null){
                        createSessionPreference(token.token)
                        goToMenu()
                    }else{
                        Toast.makeText(applicationContext, "No existe token", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(applicationContext, "Respuesta no exitosa", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InicioRespuesta>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
            }

        })
    }
}