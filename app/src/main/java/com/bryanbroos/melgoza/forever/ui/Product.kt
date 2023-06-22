package com.bryanbroos.melgoza.forever.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.ui.AppBarConfiguration
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.databinding.ActivityLoginBinding
import com.bryanbroos.melgoza.forever.api.ApiProduct
import com.bryanbroos.melgoza.forever.api.InfoUsuario
import com.bryanbroos.melgoza.forever.model.Product
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Product : AppCompatActivity() {


    private val apiProduct: ApiProduct by lazy {
        ApiProduct.create()
    }


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private lateinit var txtProductName: TextView
    private lateinit var txtPrice: TextView
    private lateinit var txtCategory:TextView
    private lateinit var txtDescripcion:TextView
    private lateinit var txtStock: TextView
    private lateinit var txtSize: TextView
    private lateinit var Shopping: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        initComponents()
        initListeners()


    }

    private fun initComponents() {
        txtProductName = findViewById(R.id.txtProductName)
        txtPrice = findViewById(R.id.txtPrice)
        txtCategory = findViewById(R.id.txtCategory)
        Shopping = findViewById(R.id.Shopping)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtStock = findViewById(R.id.txtStock)
        txtSize = findViewById(R.id.txtSize)
        val preferences = PreferenceHelper.defaultPrefs(this)
        val productId = intent.getIntExtra("productId", 0);
        val call = apiProduct.getProduct(productId, token = preferences.getString("token","")?:"")
        call.enqueue(object : Callback<Product?> {
            override fun onResponse(call: Call<Product?>, response: Response<Product?>) {
                val product = response.body()
                if(product != null){
                    txtProductName.text = product.name
                    txtPrice.text = product.price.toString()
                    txtCategory.text = product.category
                    txtDescripcion.text = product.description
                    txtStock.text = product.stock.toString()
                    txtSize.text = product.size
                }
            }

            override fun onFailure(call: Call<Product?>, t: Throwable) {
                Toast.makeText(applicationContext, "No se pudo recuperar los datos del producto", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun initListeners() {
        Shopping.setOnClickListener {
            Toast.makeText(applicationContext, "Se agrego el producto al carrito", Toast.LENGTH_SHORT).show()
        }

    }

}