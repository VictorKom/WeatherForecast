package com.myapps.weather.data

import com.myapps.weather.network.CurrentWeather
import com.myapps.weather.network.ForecastByTime
import com.myapps.weather.network.WeatherApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteRepository constructor(private val weatherApi: WeatherApi ){

    fun requestCurrentWeather(latitude: String, longitude: String): Single<CurrentWeather> {
        return weatherApi.getCurrentWeather(latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestForecastByTime(latitude: String, longitude: String): Single<ForecastByTime> {
        return weatherApi.getForecastByTime(latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}