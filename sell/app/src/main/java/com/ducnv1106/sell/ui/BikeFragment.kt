package com.ducnv1106.sell.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.ducnv1106.sell.R
import com.ducnv1106.sell.adapter.BaseAdapter
import com.ducnv1106.sell.api.ApiBuilder
import com.ducnv1106.sell.base.BaseFragment
import com.ducnv1106.sell.databinding.FragmentBikeBinding
import com.ducnv1106.sell.model.Product
import com.ducnv1106.sell.utils.Constants
import com.ducnv1106.sell.viewmodel.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("UseRequireInsteadOfGet")
class BikeFragment : BaseFragment<FragmentBikeBinding>() {

    private val adapter by lazy { BaseAdapter<Product>(context!!, R.layout.item_home) }

    override fun layoutId(): Int = R.layout.fragment_bike

    override fun getTitle(): String = "Bike"

    lateinit var data : MutableList<Product>

    private val model : HomeViewModel by activityViewModels()

    override fun initView() {
        requestApi()
        listenerRcView()
    }

    @SuppressLint("CheckResult")
    private fun requestApi() {
        ApiBuilder.service.getCar(Constants.ID_BIKE).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doFinally {

            }.subscribe({ result ->
                handlerResponse(result)
            }, { ex ->
                handlerError(ex)
            })
    }

    private fun handlerError(ex: Throwable?) {
            ex?.printStackTrace()
    }

    private fun handlerResponse(result: List<Product>?) {
        this.data= result as MutableList<Product>
        binding.listview.adapter = adapter
        adapter.data = this.data

    }

    private fun listenerRcView(){
        adapter.listener=object : BaseAdapter.BaseListener{
            override fun onItemClicked(position: Int) {
               val item= adapter.data[position]
                val intent=Intent(context,OrderProductActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_SINGLE_TOP
                intent.putExtra(Constants.PUSH_DATA,item)
                startActivity(intent)
            }

            override fun onOrderClicked(position: Int) {
                model.order(data[position])
            }


        }
    }

    override fun executeSearch(key: String) {
            val result = mutableListOf<Product>()
            if (key.isEmpty()) adapter.data=this.data
            else{
                data.forEach {
                    if (it.nameProduct.toLowerCase().contains(key.toLowerCase())){
                        result.add(it)
                    }
                }
                adapter.data=result
            }
    }

    override fun onStop() {
        Log.e("onStop","Bike")
        super.onStop()
    }

}