package com.bryanbroos.melgoza.forever.ui

import android.content.Intent
import android.media.session.MediaSession.Token
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.databinding.ActivityLoginBinding
import com.bryanbroos.melgoza.forever.api.ApiLogin
import com.bryanbroos.melgoza.forever.api.ApiProduct
import com.bryanbroos.melgoza.forever.api.InfoUsuario
import com.bryanbroos.melgoza.forever.model.Product
import com.bryanbroos.melgoza.forever.ui.Shopping
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper
import com.bryanbroos.melgoza.forever.ui.util.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Query
import java.lang.StringBuilder
import java.util.Locale

class MainMenu : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private val apiLogin: ApiLogin by lazy {
        ApiLogin.create()
    }
    private val apiProduct: ApiProduct by lazy {
        ApiProduct.create()
    }


    private lateinit var viewUser:CardView
    private lateinit var viewShopping:CardView
    private lateinit var txtName:TextView
    private lateinit var productsList: ArrayList<Product>
    private lateinit var productsAdapter: ProductsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val btnLogout = findViewById<AppCompatButton>(R.id.btnLogout)
        initComponents()
        showProducs()
        initListeners()
        btnLogout.setOnClickListener{
            Deletesessionpreference()
            val intent = Intent(this, com.bryanbroos.melgoza.forever.ui.login::class.java)
            startActivity(intent)
        }
    }


    private fun initComponents() {
        viewUser = findViewById(R.id.viewUser)
        val recyclerView = findViewById<RecyclerView>(R.id.Recyproducts)
        viewShopping = findViewById(R.id.viewShopping)
        txtName = findViewById(R.id.txtName)
        productsList = ArrayList()
        productsAdapter = ProductsAdapter(productsList)
        recyclerView.adapter = productsAdapter
        productsAdapter.setOnClickListener(object: ProductsAdapter.OnClickListener {
            override fun onClick(index: Int, product: Product) {
                val intent = Intent(applicationContext, com.bryanbroos.melgoza.forever.ui.Product::class.java)
                intent.putExtra("productId", product.id);
                startActivity(intent)
            }
        })

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
                Toast.makeText(applicationContext, "No se pudo recuperar los productos", Toast.LENGTH_SHORT).show()
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