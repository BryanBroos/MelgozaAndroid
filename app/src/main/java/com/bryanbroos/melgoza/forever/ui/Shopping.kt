package com.bryanbroos.melgoza.forever.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.forever.ui.MainMenu
import com.bryanbroos.melgoza.forever.ui.Wallet

private lateinit var viewEditWallet: CardView
private lateinit var viewPay: CardView

class Shopping : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        viewEditWallet = findViewById(R.id.viewEditWallet)
        viewPay = findViewById(R.id.viewPay)
    }
    private fun initListeners() {
        viewEditWallet.setOnClickListener{

            val intent = Intent(this, Wallet::class.java)
            startActivity(intent)

        }

        viewPay.setOnClickListener{

            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)

        }

    }
}