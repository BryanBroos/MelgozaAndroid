package com.bryanbroos.melgoza.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.bryanbroos.melgoza.R

private lateinit var viewEditWallet: CardView
private lateinit var viewPay: CardView

class Product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

    }
}