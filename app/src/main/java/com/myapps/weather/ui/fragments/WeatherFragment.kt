package com.myapps.weather.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapps.weather.App
import com.myapps.weather.R
import com.myapps.weather.data.network.CurrentWeather
import com.myapps.weather.data.network.DailyWeather
import com.myapps.weather.data.network.WeatherByTime
import com.myapps.weather.ui.activity.MainActivityView
import com.myapps.weather.ui.adapters.DailyWeatherAdapter
import com.myapps.weather.ui.adapters.ForecastByTimeAdapter
import com.myapps.weather.utils.Symbols.CELSIUS_DEGREE_SYMBOL
import com.myapps.weather.utils.Symbols.PLUS
import com.myapps.weather.utils.setWeatherIcon
import kotlinx.android.synthetic.main.fragment_weather.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class WeatherFragment : MvpAppCompatFragment(), WeatherView {

    private val forecastByTimeAdapter by lazy { ForecastByTimeAdapter() }
    private val dailyWeatherAdapter by lazy { DailyWeatherAdapter() }

    @Inject
    @InjectPresenter
    lateinit var weatherPresenter: WeatherPresenter

    @ProvidePresenter
    fun providePresenter(): WeatherPresenter = weatherPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.INSTANCE.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        forecastByTime.adapter = forecastByTimeAdapter
        dailyWeather.adapter = dailyWeatherAdapter
        getCurrentWeather()
        getForecast()
    }

    override fun updateForecastByTime(forecastByTimeList: ArrayList<WeatherByTime>) {
        forecastByTimeAdapter.updateForecastByTime(forecastByTimeList)
    }

    override fun updateDailyWeather(dailyWeatherList: ArrayList<DailyWeather>) {
        dailyWeatherAdapter.updateForecastByTime(dailyWeatherList)
    }

    override fun updateCurrentWeather(currentWeather: CurrentWeather) {
        updateTemperature(currentWeather)
        updatePressure(currentWeather)
        updateWindSpeed(currentWeather)
        toolbar.title = currentWeather.name
        weatherDescription.text = currentWeather.weather[0].description
        rightNow.text = resources.getString(R.string.rightNow)
        weatherIcon.setWeatherIcon(currentWeather.weather[0].icon)
    }

    override fun networkError() {
        (activity as MainActivityView).showNetworkError()
    }

    override fun hideProgressBar() {
        progressBar.hide()
    }

    private fun getCurrentWeather() {
        val latitude = arguments?.getString("latitude")
        val longitude = arguments?.getString("longitude")
        if (latitude != null && longitude != null) {
            weatherPresenter.getCurrentWeather(latitude, longitude)
        }
    }

    private fun getForecast() {
        val latitude = arguments?.getString("latitude")
        val longitude = arguments?.getString("longitude")
        if (latitude != null && longitude != null) {
            weatherPresenter.getForecast(latitude, longitude)
        }
    }

    private fun updateTemperature(currentWeather: CurrentWeather) {
        val temp = currentWeather.main.temp.toInt()
        val temperatureStr =
            if (temp > 0) "$PLUS$temp$CELSIUS_DEGREE_SYMBOL" else "$temp$CELSIUS_DEGREE_SYMBOL"
        temperature.text = temperatureStr
    }

    private fun updatePressure(currentWeather: CurrentWeather) {
        val press = (currentWeather.main.pressure * 0.750062).toInt()
        val pressureStr =
            resources.getString(R.string.pressure) + " $press " + resources.getString(R.string.pressureUnit)
        pressure.text = pressureStr
    }

    private fun updateWindSpeed(currentWeather: CurrentWeather) {
        val windSpeedStr =
            resources.getString(R.string.windSpeed) + " ${currentWeather.wind.speed} " + resources.getString(
                R.string.windSpeedUnit
            )
        windSpeed.text = windSpeedStr
    }
}
