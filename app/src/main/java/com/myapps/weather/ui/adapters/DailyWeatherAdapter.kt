package com.myapps.weather.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapps.weather.App
import com.myapps.weather.R
import com.myapps.weather.network.DailyWeather
import com.myapps.weather.utils.inflate
import com.myapps.weather.utils.setWeatherIcon
import kotlinx.android.synthetic.main.fragment_weather.*

class DailyWeatherAdapter : RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder>() {

    private var dailyWeatherList = ArrayList<DailyWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.daily_weather))
    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) dailyWeatherList[0].date = App.INSTANCE.applicationContext.getString(R.string.tomorrow)
        holder.onBind(dailyWeatherList[position])
    }

    fun updateForecastByTime(updDailyWeatherList: ArrayList<DailyWeather>) {
        dailyWeatherList.clear()
        dailyWeatherList.addAll(updDailyWeatherList)
        notifyDataSetChanged()
    }

    class ViewHolder(val weather: View) : RecyclerView.ViewHolder(weather) {
        fun onBind(dailyWeather: DailyWeather) {
            val date = weather.findViewById<TextView>(R.id.dailyDate)
            date.text = dailyWeather.date

            val dayOfWeek = weather.findViewById<TextView>(R.id.dayOfWeek)
            dayOfWeek.text = dailyWeather.dayOfWeek

            val dayTemp = weather.findViewById<TextView>(R.id.dayTemp)
            val dTemp = dailyWeather.dayTemp
            dayTemp.text = if (dTemp > 0) "+$dTemp°" else "$dTemp°"

            val nightTemp = weather.findViewById<TextView>(R.id.nightTemp)
            val nTemp = dailyWeather.nightTemp
            nightTemp.text = if (nTemp > 0) "+$nTemp°" else "$nTemp°"

            val weatherIcon = weather.findViewById<ImageView>(R.id.dailyWeatherIcon)
            weatherIcon.setWeatherIcon(dailyWeather.icon)
        }
    }

}