package com.ducnv1106.sell.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.ducnv1106.sell.adapter.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ducnv1106.sell.R
import com.ducnv1106.sell.api.ApiBuilder
import com.ducnv1106.sell.databinding.ActivityOrderProductBinding
import com.ducnv1106.sell.model.OrderProduct
import com.ducnv1106.sell.model.Product
import com.ducnv1106.sell.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OrderProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderProductBinding

    private val adapter by lazy { BaseAdapter<OrderProduct>(this,R.layout.item_order_product) }

    private lateinit var product:Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_order_product)
        initView()

    }

    private fun initView() {
        product= intent.getSerializableExtra(Constants.PUSH_DATA) as Product
        requsetApi()
        initActionbar()
    }

    private fun requsetApi() {
        ApiBuilder.service.getOrderProduct(product.id.toIntOrNull()!!).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doFinally({

            }).subscribe({ result ->
                handlerResponse(result)
            }, { ex ->
                handlerError(ex)
            })
    }

    private fun handlerError(ex: Throwable?) {
            Log.e("result",ex?.message)
    }

    private fun handlerResponse(result: List<OrderProduct>?) {
        binding.listview.adapter=adapter
        adapter.data=result as MutableList<OrderProduct>
        adapter.image=product.imageProduct
    }

    private fun initActionbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.title=resources.getString(R.string.str_title_toolbar_order_product)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}