package com.android.sapo.data

import com.android.sapo.data.remote.ApiHelper

class AppDataManager constructor(private val apiHelper: ApiHelper) : DataManager {

    override fun getCity() = apiHelper.getCity()

    override fun getDistrict() = apiHelper.getDistrict()
}