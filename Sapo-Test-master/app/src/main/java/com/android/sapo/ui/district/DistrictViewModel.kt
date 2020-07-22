package com.android.sapo.ui.district

import com.android.sapo.data.DataManager
import com.android.sapo.data.model.City
import com.android.sapo.data.model.District
import com.android.sapo.data.remote.ApiConstant
import com.core.BaseViewModel
import com.google.gson.Gson
import com.utils.SchedulerProvider
import com.utils.ext.toList
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

interface DistrictInput {

}

interface DistrictOutput {
    val district: Observable<MutableList<District>>
    val isLoading: Observable<Boolean>
}

class DistrictViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider,
        private val gson: Gson
) : BaseViewModel<DistrictInput, DistrictOutput, DataManager>(dataManager, schedulerProvider), DistrictInput, DistrictOutput {

    override val input: DistrictInput
        get() = this

    override val output: DistrictOutput
        get() = this

    override val district: PublishSubject<MutableList<District>> = PublishSubject.create()

    override val isLoading: PublishSubject<Boolean> = PublishSubject.create()

    fun getDistrict(city: City?): Disposable {
        isLoading.onNext(true)
        return dataManager.getDistrict()
                .map {
                    val d: List<District> = gson.toList(it.getAsJsonArray(ApiConstant.DISTRICTS))
                    d.filter { c ->
                        c.cityCode == city?.code
                    }.toMutableList()
                }
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({
                    district.onNext(it)
                    isLoading.onNext(false)
                }, {
                    isLoading.onNext(false)
                    it.printStackTrace()
                })
    }
}