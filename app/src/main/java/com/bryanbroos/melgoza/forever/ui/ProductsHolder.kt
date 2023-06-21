package com.bryanbroos.melgoza.forever.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.forever.model.Product
import com.bryanbroos.melgoza.forever.ui.ProductsAdapter

class ProductsHolder(view:View):RecyclerView.ViewHolder(view) {

    fun bind(product: Product){
        itemView.findViewById<TextView>(R.id.txtProductName).text = product.name
        itemView.findViewById<TextView>(R.id.txtPrice).text = product.price.toString()
    }
}