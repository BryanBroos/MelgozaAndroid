package com.bryanbroos.melgoza.forever.model

data class Shopping(
    val idShopping: Int,

    val state: Boolean,

    val idPerson: Int,  //llave foranea de user

    val idProduct: Int //llave foranea de producto



)
