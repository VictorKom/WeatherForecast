package com.myapps.weather.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?lang=ru&units=metric&appid=70c1437031f8a5ef243d5403cefa285c")
    fun getForecastByTime(@Query("lat") latitude: String, @Query("lon") longitude: String): Single<ForecastByTime>

    @GET("weather?lang=ru&units=metric&appid=70c1437031f8a5ef243d5403cefa285c")
    fun getCurrentWeather(@Query("lat") latitude: String, @Query("lon") longitude: String): Single<CurrentWeather>
}