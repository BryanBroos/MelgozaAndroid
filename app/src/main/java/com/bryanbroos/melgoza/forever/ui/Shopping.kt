package com.bryanbroos.melgoza.forever.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.forever.api.ApiLogin
import com.bryanbroos.melgoza.forever.api.ApiProduct
import com.bryanbroos.melgoza.forever.api.InfoUsuario
import com.bryanbroos.melgoza.forever.model.Product
import com.bryanbroos.melgoza.forever.ui.MainMenu
import com.bryanbroos.melgoza.forever.ui.Wallet
import androidx.recyclerview.widget.RecyclerView
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var viewEditWallet: CardView
private lateinit var viewPay: CardView
private val apiLogin: ApiLogin by lazy {
    ApiLogin.create()
}
private val apiProduct: ApiProduct by lazy {
    ApiProduct.create()
}
class Shopping : AppCompatActivity() {


    private lateinit var productsList: ArrayList<Product>
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        initComponents()
        showProducs()
        initListeners()
    }

    private fun initComponents() {
        viewEditWallet = findViewById(R.id.viewEditWallet)
        viewPay = findViewById(R.id.viewPay)
        val recyclerView = findViewById<RecyclerView>(R.id.Recyproduct)
        productsList = ArrayList()
        productsAdapter = ProductsAdapter(productsList)
        recyclerView.adapter = productsAdapter

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

private fun showProducs(){
    val preferences = PreferenceHelper.defaultPrefs(this)
    val call = apiProduct.getAllProducts(token = preferences.getString("token","")?:"")
    call.enqueue(object : Callback<List<Product>> {
        override fun onResponse(
            call: Call<List<Product>>,
            response: Response<List<Product>>
        ) {
            if(response.isSuccessful){
                val productList = response.body()
                if(productList != null){
                    productsList.clear()
                    productsList.addAll(productList)
                    productsAdapter.notifyDataSetChanged()
                }

            }else{
                Toast.makeText(applicationContext, "Error al recuperar los productos", Toast.LENGTH_SHORT).show()
            }


        }

        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            Toast.makeText(applicationContext, "Error en el servidor", Toast.LENGTH_SHORT).show()
        }
    })

}
}