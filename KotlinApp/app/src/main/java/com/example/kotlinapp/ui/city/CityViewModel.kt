package com.example.kotlinapp.ui.city

import com.core.BaseViewModel
import com.example.kotlinapp.data.AppDataManager
import com.example.kotlinapp.data.DataManager
import com.example.kotlinapp.data.model.City
import com.example.kotlinapp.data.remote.AppConstant
import com.google.gson.Gson
import com.utils.SchedulerProvider
import com.utils.ext.toList
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.*

interface CityInput {

}

interface CityOutput {
    val city: Observable<MutableList<City>>
    val isLoading: Observable<Boolean>

}

class CityViewModel(

    dataManager: AppDataManager,
    schedulerProvider: SchedulerProvider,
    private val gson: Gson

) : BaseViewModel<CityInput, CityOutput, DataManager>(dataManager, schedulerProvider), CityInput,
    CityOutput {


    override val city: PublishSubject<MutableList<City>> = PublishSubject.create()


    override val isLoading: PublishSubject<Boolean> = PublishSubject.create()


    override val input: CityInput
        get() = this

    override val output: CityOutput
        get() = this
//
//    fun getCity (): Disposable {
//        isLoading.onNext(true)
//        return dataManager.getCity().map {
//            val searchModels: List<City> = gson.toList(it.getAsJsonObject(AppConstant.CITIES))
//            searchModels.toMutableList()
//        }.compose(schedulerProvider.ioToMainSingleScheduler())
//            .subscribe({
//                city.onNext(it)
//                isLoading.onNext(false)
//            }, {
//                isLoading.onNext(false)
//                it.printStackTrace()
//            })
//    }

    fun getCity(): Disposable {
        isLoading.onNext(true)
        return dataManager.getCity()
            .map {
                val searchModels: List<City> = gson.toList(it.getAsJsonArray(AppConstant.CITIES))
                searchModels.toMutableList()
            }
            .compose(schedulerProvider.ioToMainSingleScheduler())
            .subscribe({
                city.onNext(it)
                isLoading.onNext(false)
            }, {
                isLoading.onNext(false)
                it.printStackTrace()
            })
    }


}
