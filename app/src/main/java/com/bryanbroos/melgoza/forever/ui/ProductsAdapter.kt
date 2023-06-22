package com.bryanbroos.melgoza.forever.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bryanbroos.melgoza.R
import com.bryanbroos.melgoza.forever.model.Product


class ProductsAdapter(val productlist:ArrayList<Product>): RecyclerView.Adapter<ProductsHolder>(){
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producitem, parent, false)
        return ProductsHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val product = productlist[position]
        holder.itemView.setOnClickListener {
            if(this.onClickListener != null) {
                this.onClickListener!!.onClick(position, product)
            }
        }
        holder.bind(product)
    }


    override fun getItemCount(): Int {
        return productlist.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener;
    }

    interface OnClickListener {
        fun onClick(index: Int, product: Product)
    }

}