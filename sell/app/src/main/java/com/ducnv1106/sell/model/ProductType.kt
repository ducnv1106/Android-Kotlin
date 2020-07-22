package com.ducnv1106.sell.model

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class ProductType (
    val id:Int,
    val name:String,
    @DrawableRes
    val image:Int?,
    val typeHeader:Boolean
)