package com.myapps.weather

import android.app.Application
import com.myapps.weather.di.AppComponent
import com.myapps.weather.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder().build()
    }
}