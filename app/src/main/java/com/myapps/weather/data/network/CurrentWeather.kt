package com.myapps.weather.data.network

data class CurrentWeather(
    val name: String,
    val main: MainParameters,
    val weather: ArrayList<MainWeather>,
    val wind: Wind
)