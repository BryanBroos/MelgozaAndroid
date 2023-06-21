package com.bryanbroos.melgoza.forever.model

data class Wallet(
    val idWallet: Int,

    val direction: String,

    val numberCard: Int,


    val idPerson: Int //Llave foranea de User


)