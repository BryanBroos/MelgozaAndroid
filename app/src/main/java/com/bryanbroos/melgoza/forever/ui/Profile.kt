package com.bryanbroos.melgoza.forever.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.ui.AppBarConfiguration
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.databinding.ActivityLoginBinding
import com.bryanbroos.melgoza.forever.api.ApiLogin
import com.bryanbroos.melgoza.forever.api.InfoUsuario
import com.bryanbroos.melgoza.forever.api.InicioRespuesta
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper.get
import retrofit2.Call
import retrofit2.Response

class Profile : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private val apiLogin: ApiLogin by lazy {
        ApiLogin.create()
    }
    private lateinit var viewEditWallet:CardView
    private lateinit var txtName:TextView
    private lateinit var txtReal:TextView
    private lateinit var txtMaternalName:TextView
    private lateinit var txtPaternalName:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initComponents()
        initListeners()
    }
    private fun initComponents() {
        viewEditWallet = findViewById(R.id.viewEditWallet)
        txtName = findViewById(R.id.txtName)
        txtReal = findViewById(R.id.txtReal)
        txtMaternalName = findViewById(R.id.txtMaternalName)
        txtPaternalName = findViewById(R.id.txtPaternalName)
        val preferences = PreferenceHelper.defaultPrefs(this)
        val call = apiLogin.getCustomer(token = preferences.getString("token","")?:"")
        call.enqueue(object : retrofit2.Callback<InfoUsuario>{
            override fun onResponse(call: Call<InfoUsuario>, response: Response<InfoUsuario>) {
                val user = response.body()
                if(user != null){
                    txtName.setText(user.name)
                    txtReal.setText(user.username)
                    txtMaternalName.setText(user.maternalSurname)
                    txtPaternalName.setText(user.paternalSurname)

                }
            }
            override fun onFailure(call: Call<InfoUsuario>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun initListeners() {
        viewEditWallet.setOnClickListener{

            val intent = Intent(this, Wallet::class.java)
            startActivity(intent)

        }

    }

}