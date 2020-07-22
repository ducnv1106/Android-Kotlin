package com.ducnv1106.sell.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product (
     @SerializedName("id")
     val id:String,
     @SerializedName("nameProduct")
     val nameProduct:String,
     @SerializedName("priceProduct")
     val priceProduct:String,
     @SerializedName("imageProduct")
     val imageProduct:String,
     @SerializedName("description")
     val description:String,
     @SerializedName("idProduct")
     val  idProduct:String
 ): Serializable