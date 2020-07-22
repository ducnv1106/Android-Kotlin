package com.android.sapo.di

import com.android.sapo.data.AppDataManager
import com.android.sapo.data.DataManager
import com.android.sapo.data.remote.ApiHelper
import com.android.sapo.data.remote.AppApiHelper
import com.google.gson.GsonBuilder
import com.utils.SchedulerProvider
import org.koin.core.module.Module
import org.koin.dsl.module



//define app module gson, data manager, etc...
val appModule: Module = module {

    single { SchedulerProvider() }

    single { AppDataManager(get()) as DataManager }

    single { AppApiHelper() as ApiHelper }

    single {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'X'").create()!!
    }
}
val sapoModule = listOf(appModule, viewModule)
