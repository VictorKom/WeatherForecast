package com.myapps.weather.utils

import com.myapps.weather.network.DailyWeather
import com.myapps.weather.network.WeatherByTime
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ForecastConverter {

    fun convertToDailyWeather(forecastByTimeList: ArrayList<WeatherByTime>): ArrayList<DailyWeather> {
        val dailyWeatherList = ArrayList<DailyWeather>()
        var currentDate = getDay(forecastByTimeList[0].dt_txt)
        for (i in 1..4) {
            currentDate ++
            if (currentDate % 30 != 0) currentDate %= 30
            val currentWeather = DailyWeather(0, 0, "", "", "")
            for (weather in forecastByTimeList){
                val day = getDay(weather.dt_txt)
                val time = getTime(weather.dt_txt)
                if (day == currentDate) {
                    if (time == 15) {
                        currentWeather.date = getDate(weather.dt_txt)
                        currentWeather.dayOfWeek = getDayOfWeek(weather.dt_txt)
                        currentWeather.dayTemp = weather.main.temp.toInt()
                        currentWeather.icon = weather.weather[0].icon
                    }
                    if (time == 0) currentWeather.nightTemp = weather.main.temp.toInt()
                }
            }
            dailyWeatherList.add(currentWeather)
        }
        return dailyWeatherList
    }

    private fun getDay(dateStr: String): Int {
        val date = parseDate(dateStr)
        return if (date != null) SimpleDateFormat("d", Locale.getDefault()).format(date).toInt() else 0
    }

    private fun getTime(dateStr: String): Int {
        val date = parseDate(dateStr)
        return if (date != null) SimpleDateFormat("HH", Locale.getDefault()).format(date).toInt() else 0
    }

    private fun getDate(dateStr: String): String {
        val date = parseDate(dateStr)
        return if (date != null) SimpleDateFormat("d MMMM", Locale.getDefault()).format(date) else ""
    }

    private fun getDayOfWeek(dateStr: String): String {
        val date = parseDate(dateStr)
        return if (date != null) SimpleDateFormat("EEEE", Locale.getDefault()).format(date) else ""
    }

    private fun parseDate(date: String) = SimpleDateFormat("y-MM-d HH:mm:ss", Locale.getDefault()).parse(date)
}