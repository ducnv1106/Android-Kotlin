package com.example.kotlinapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City(

    @Expose
    @SerializedName("Name")
    var name: String,
    @Expose
    @SerializedName("CityCode")
    var code: Int,
    @Expose
    var selected: Boolean = false


)