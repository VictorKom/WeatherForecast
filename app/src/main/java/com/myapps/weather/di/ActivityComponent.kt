package com.myapps.weather.di

import com.myapps.weather.ui.activities.MainActivity
import com.myapps.weather.ui.fragments.LocationOffFragment
import com.myapps.weather.ui.fragments.NetworkOffFragment
import com.myapps.weather.ui.fragments.WeatherFragment
import dagger.Component

@Component(modules = [PresenterModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(weatherFragment: WeatherFragment)
}