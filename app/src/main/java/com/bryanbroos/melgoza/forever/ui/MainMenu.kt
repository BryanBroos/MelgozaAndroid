package com.bryanbroos.melgoza.forever.ui

import android.content.Intent
import android.media.session.MediaSession.Token
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.navigation.ui.AppBarConfiguration
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.databinding.ActivityLoginBinding
import com.bryanbroos.melgoza.forever.api.ApiLogin
import com.bryanbroos.melgoza.forever.api.InfoUsuario
import com.bryanbroos.melgoza.forever.ui.Shopping
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Response

class MainMenu : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private val apiLogin: ApiLogin by lazy {
        ApiLogin.create()
    }

    private lateinit var viewUser:CardView
    private lateinit var viewShopping:CardView
    private lateinit var txtName:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val btnLogout = findViewById<AppCompatButton>(R.id.btnLogout)
        initComponents()
        initListeners()


        btnLogout.setOnClickListener{
            Deletesessionpreference()
            val intent = Intent(this, com.bryanbroos.melgoza.forever.ui.login::class.java)

            startActivity(intent)
        }
    }



    private fun initComponents() {
        viewUser = findViewById(R.id.viewUser)
        viewShopping = findViewById(R.id.viewShopping)
        txtName = findViewById(R.id.txtName)
        val preferences = PreferenceHelper.defaultPrefs(this)
        val call = apiLogin.getCustomer(token = preferences.getString("token","")?:"")
        call.enqueue(object : retrofit2.Callback<InfoUsuario>{
            override fun onResponse(call: Call<InfoUsuario>, response: Response<InfoUsuario>) {
                val user = response.body()
                if(user != null){
                    txtName.setText(user.name)

                }
            }
            override fun onFailure(call: Call<InfoUsuario>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun initListeners() {
     viewUser.setOnClickListener{

             val intent = Intent(this, Profile::class.java)
            startActivity(intent)

     }
      viewShopping.setOnClickListener {
          val intent = Intent(this, Shopping::class.java)
          startActivity(intent)
      }

    }

    private fun Deletesessionpreference(){

        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["token"] = null

    }
}