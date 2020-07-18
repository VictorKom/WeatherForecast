package com.myapps.weather.data.network.api

import com.myapps.weather.data.network.CurrentWeather
import com.myapps.weather.data.network.ForecastByTime
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?lang=ru&units=metric")
    fun getForecastByTime(@Query("lat") latitude: String, @Query("lon") longitude: String): Single<ForecastByTime>

    @GET("weather?lang=ru&units=metric")
    fun getCurrentWeather(@Query("lat") latitude: String, @Query("lon") longitude: String): Single<CurrentWeather>
}