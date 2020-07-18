package com.myapps.weather.di

import com.myapps.weather.App
import com.myapps.weather.ui.activity.MainActivity
import com.myapps.weather.ui.fragments.WeatherFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(weatherFragment: WeatherFragment)

    fun inject(appComponent: App)
}

