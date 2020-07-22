package com.example.kotlinapp.data

import com.example.kotlinapp.data.remote.ApiHelper
import com.google.gson.JsonObject
import io.reactivex.Single

class AppDataManager constructor(private val apiHelper: ApiHelper) : DataManager {

    override fun getCity()= apiHelper.getCity()

    override fun getDistrict() = apiHelper.getDistrict()


}