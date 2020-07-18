package com.myapps.weather.ui.fragments

import com.myapps.weather.data.network.CurrentWeather
import com.myapps.weather.data.network.DailyWeather
import com.myapps.weather.data.network.WeatherByTime
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface WeatherView : MvpView {
    fun updateForecastByTime(forecastByTimeList: ArrayList<WeatherByTime>)

    fun updateDailyWeather(dailyWeatherList: ArrayList<DailyWeather>)

    fun updateCurrentWeather(currentWeather: CurrentWeather)

    @StateStrategyType(SkipStrategy::class)
    fun networkError()

    @StateStrategyType(SkipStrategy::class)
    fun hideProgressBar()
}