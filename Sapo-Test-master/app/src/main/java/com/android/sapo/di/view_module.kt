package com.android.sapo.di

import com.android.sapo.ui.city.CityViewModel
import com.android.sapo.ui.district.DistrictViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModule: Module = module {

    viewModel { CityViewModel(get(), get(), get()) }
    viewModel { DistrictViewModel(get(), get(), get()) }
}