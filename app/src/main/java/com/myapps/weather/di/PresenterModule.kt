package com.myapps.weather.di

import com.myapps.weather.data.RemoteRepository
import com.myapps.weather.ui.activities.MainActivityPresenter
import com.myapps.weather.ui.fragments.WeatherPresenter
import dagger.Module
import dagger.Provides


@Module
class PresenterModule {

    @ActivityScope
    @Provides
    fun provideMainPresenter(): MainActivityPresenter = MainActivityPresenter()

    @ActivityScope
    @Provides
    fun provideWeatherPresenter(remoteRepository: RemoteRepository): WeatherPresenter = WeatherPresenter(remoteRepository)
}