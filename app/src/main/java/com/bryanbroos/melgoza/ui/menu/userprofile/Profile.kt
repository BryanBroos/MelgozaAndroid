package com.bryanbroos.melgoza.ui.menu.userprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.bryanbroos.melgoza.R

class Profile : AppCompatActivity() {

    private lateinit var viewEditWallet:CardView
    private lateinit var viewEditUser:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initComponents()
        initListeners()
    }
    private fun initComponents() {
        viewEditWallet = findViewById(R.id.viewEditWallet)
        viewEditUser = findViewById(R.id.viewEditUser)
    }
    private fun initListeners() {
        viewEditWallet.setOnClickListener{

            val intent = Intent(this, Wallet::class.java)
            startActivity(intent)

        }

        viewEditUser.setOnClickListener{

            val intent = Intent(this, EditUser::class.java)
            startActivity(intent)

        }

    }

}