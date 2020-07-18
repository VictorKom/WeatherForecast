package com.myapps.weather.data.network

data class DailyWeather(
    var dayTemp: Int,
    var nightTemp: Int,
    var date: String,
    var dayOfWeek: String,
    var icon: String
)