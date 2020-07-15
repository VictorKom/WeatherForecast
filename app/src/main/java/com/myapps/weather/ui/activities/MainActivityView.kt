package com.myapps.weather.ui.activities

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.lang.Exception

@StateStrategyType(SkipStrategy::class)
interface MainActivityView : MvpView {

    fun startResolutionForResult(exception: Exception)

    fun checkPermission()

    fun showLocationError()

    fun showNetworkError()

    @StateStrategyType(AddToEndStrategy::class)
    fun showForecast(lat: String, lon: String)
}