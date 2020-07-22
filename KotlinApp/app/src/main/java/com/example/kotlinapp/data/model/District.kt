package com.example.kotlinapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class District (

    @Expose
    @SerializedName("CityCode")
    var cityCode:Int?,

    @Expose
    @SerializedName("DistrictCode")
    var districtCode:Int?,

    @Expose
    @SerializedName("Name")
    var name:String?,
    @Expose
    var selected:Boolean=false



)