package com.example.kotlinapp.di

import com.example.kotlinapp.data.AppDataManager
import com.example.kotlinapp.data.remote.ApiHelper
import com.example.kotlinapp.data.remote.AppApiHelper
import com.example.kotlinapp.ui.city.CityViewModel
import com.google.gson.GsonBuilder
import com.utils.SchedulerProvider
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModule: Module = module {

    viewModel { CityViewModel(get(), get(), get()) }
}