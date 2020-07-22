package com.example.kotlinapp

import androidx.multidex.MultiDexApplication
import com.example.kotlinapp.di.sapoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class MyApplication : MultiDexApplication() {

    companion object{
        lateinit var myApplication: MyApplication

        @JvmStatic
        @Synchronized
        fun getInstance(): MyApplication{
            return myApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        myApplication = this
        startKoin { 
            androidContext(this@MyApplication)
            modules(sapoModule)
            logger(EmptyLogger())
        }
    }
}