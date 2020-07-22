package com.android.sapo

import androidx.multidex.MultiDexApplication
import com.android.sapo.di.sapoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class SapoApplication : MultiDexApplication() {

    companion object {

        lateinit var sapoApplication: SapoApplication

        @JvmStatic
        @Synchronized
        fun getInstance(): SapoApplication {
            return sapoApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        sapoApplication = this

        startKoin {
            androidContext(this@SapoApplication)
            modules(sapoModule)
            logger(EmptyLogger())
        }
    }
}