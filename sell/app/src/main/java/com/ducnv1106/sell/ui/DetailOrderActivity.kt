package com.ducnv1106.sell.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ducnv1106.sell.R
import com.ducnv1106.sell.adapter.DetailOrderAdapter
import com.ducnv1106.sell.databinding.ActivityOrderDetailBinding
import com.ducnv1106.sell.viewmodel.HomeViewModel

class DetailOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderDetailBinding

    private val model by lazy { HomeViewModel() }

    private val adapter by lazy { DetailOrderAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail)
        setUpViewModel()
        initActionbar()
        initRcView()
        initTvPrice()
    }
    fun setUpViewModel(){
        model.getAmount().observe(this, Observer {
            initTvPrice()

        })
    }

    private fun initActionbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title=resources.getString(R.string.str_title_toolbar_order_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun initRcView(){
        binding.listview.adapter=adapter
        adapter.data=model.getData()
        Log.e("result",adapter.data.size.toString())
    }

    private fun initTvPrice(){
        var price=0
        model.getData().forEach {
            price+=it.priceProduct.toInt()
        }
        binding.tvPrice.text="Price: "+price.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}