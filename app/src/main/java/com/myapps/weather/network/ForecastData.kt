package com.myapps.weather.network

data class ForecastByTime(val list: ArrayList<WeatherByTime>)

data class WeatherByTime(val main: MainParameters, val weather: ArrayList<MainWeather>, val dt_txt: String)

data class CurrentWeather(val name: String, val main: MainParameters, val weather: ArrayList<MainWeather>, val wind: Wind)

data class MainParameters(val temp: Double, val pressure: Int)

data class MainWeather(val description: String, val icon: String)

data class Wind(val speed: Int, val deg: Int)

data class DailyWeather(var dayTemp: Int, var nightTemp: Int, var date: String, var dayOfWeek: String, var icon: String)