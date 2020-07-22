package com.ducnv1106.sell.model

import com.google.gson.annotations.SerializedName

data class OrderProduct  (
    @SerializedName("name")
    val name:String,
    @SerializedName("phoneNumber")
    val phoneNumber:String,
    @SerializedName("address")
    val address:String,
    @SerializedName("idProduct")
    val idProduct:String,
    @SerializedName("price")
    val price:String
)