package com.myapps.weather.ui.fragments

import android.util.Log
import com.myapps.weather.App
import com.myapps.weather.data.RemoteRepository
import com.myapps.weather.utils.ForecastConverter
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class WeatherPresenter constructor(private val remoteRepository: RemoteRepository) : MvpPresenter<WeatherView>() {

    private val disposables = CompositeDisposable()
    private val forecastConverter = ForecastConverter()

    fun getCurrentWeather(latitude: String, longitude: String) {
        val currentWeather = remoteRepository.requestCurrentWeather(latitude, longitude)
            .subscribe(
                { currentWeather ->
                    viewState.hideProgressBar()
                    viewState.updateCurrentWeather(currentWeather)
                },
                { e ->
                    viewState.networkError()
                    Log.d("MyTag", "err ${e.message}")
                })
        disposables.add(currentWeather)
    }

    fun getForecast(latitude: String, longitude: String) {
        val forecastByTime = remoteRepository.requestForecastByTime(latitude, longitude)
            .subscribe(
                { forecast ->
                    viewState.updateForecastByTime(ArrayList(forecast.list.subList(0, 15)))
                    viewState.updateDailyWeather(forecastConverter.convertToDailyWeather(forecast.list))
                },
                { e ->
                    Log.d("MyTag", "err ${e.message}")
                })
        disposables.add(forecastByTime)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}