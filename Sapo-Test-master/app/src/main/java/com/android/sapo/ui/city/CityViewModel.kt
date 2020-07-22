package com.android.sapo.ui.city

import com.android.sapo.data.DataManager
import com.android.sapo.data.model.City
import com.android.sapo.data.remote.ApiConstant.CITIES
import com.core.BaseViewModel
import com.google.gson.Gson
import com.utils.SchedulerProvider
import com.utils.ext.toList
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

interface CityInput {

}

interface CityOutput {
    val city: Observable<MutableList<City>>
    val isLoading: Observable<Boolean>
}

class CityViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, private val gson: Gson)
    : BaseViewModel<CityInput, CityOutput, DataManager>(dataManager, schedulerProvider), CityInput, CityOutput {

    override val input: CityInput
        get() = this

    override val output: CityOutput
        get() = this

    override val city: PublishSubject<MutableList<City>> = PublishSubject.create()

    override val isLoading: PublishSubject<Boolean> = PublishSubject.create()

    fun getCity(): Disposable {
        isLoading.onNext(true)
        return dataManager.getCity()
                .map {
                    val searchModels: List<City> = gson.toList(it.getAsJsonArray(CITIES))
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