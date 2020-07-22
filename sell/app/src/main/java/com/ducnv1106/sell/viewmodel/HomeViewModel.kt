package com.ducnv1106.sell.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ducnv1106.sell.model.Product

class HomeViewModel : ViewModel() {

    companion object{
        val amountOrder=MutableLiveData<Int>()
        val data= mutableListOf<Product>()
    }


    fun initAmountOrder(){
        amountOrder.value= 0
    }

    fun order(item:Product){
        amountOrder.value= amountOrder.value?.plus(1)
        data.add(item)
    }
    fun unOrder(){
        amountOrder.value= amountOrder.value?.minus(1)

    }

    fun plusOrder(){
        amountOrder.value= amountOrder.value?.plus(1)
    }

    fun getAmount(): LiveData<Int> {
        return amountOrder
    }
    fun getData(): MutableList<Product> {
        return data
    }

}