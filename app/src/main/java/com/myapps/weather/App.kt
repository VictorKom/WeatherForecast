package com.myapps.weather

import android.app.Application
import com.myapps.weather.di.AppComponent
import com.myapps.weather.di.AppModule
import com.myapps.weather.di.DaggerAppComponent
import com.myapps.weather.di.NetworkModule

class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}