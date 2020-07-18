package com.myapps.weather.data.network

data class WeatherByTime(
    val main: MainParameters,
    val weather: ArrayList<MainWeather>,
    val dt_txt: String
)