package com.android.sapo.data.remote

import com.android.sapo.data.remote.ApiEndPoint.CITY_URL
import com.android.sapo.data.remote.ApiEndPoint.DISTRICT_URL
import com.google.gson.JsonObject
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class AppApiHelper : ApiHelper {

    override fun getCity(): Single<JsonObject> = Rx2AndroidNetworking.get(CITY_URL)
            .build()
            .getObjectSingle(JsonObject::class.java)

    override fun getDistrict(): Single<JsonObject> = Rx2AndroidNetworking.get(DISTRICT_URL)
            .build()
            .getObjectSingle(JsonObject::class.java)
}