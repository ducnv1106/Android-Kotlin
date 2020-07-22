package com.ducnv1106.sell.api

import com.ducnv1106.sell.model.OrderProduct
import com.ducnv1106.sell.model.Product
import com.ducnv1106.sell.model.ProductType
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

    @GET("loaisanpham.php")
    fun getProductType(): Observable<List<ProductType>>

    @POST("getProduct.php")
    @FormUrlEncoded
    fun getCar(@Field("id_product") id_product: Int): Observable<List<Product>>

    @POST("getOrderProduct.php")
    @FormUrlEncoded
    fun getOrderProduct(@Field("id_product") id_product: Int):Observable<List<OrderProduct>>

}