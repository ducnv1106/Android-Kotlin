package com.example.kotlinapp.di

import com.example.kotlinapp.data.AppDataManager
import com.example.kotlinapp.data.remote.ApiHelper
import com.example.kotlinapp.data.remote.AppApiHelper
import com.google.gson.GsonBuilder
import com.utils.SchedulerProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    single { SchedulerProvider() }
    single { AppDataManager(get()) as ApiHelper }
    single { AppApiHelper() as ApiHelper }
    single {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'X'").create()
    }

}

val  sapoModule = listOf(appModule,viewModule)