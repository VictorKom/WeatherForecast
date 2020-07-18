package com.myapps.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapps.weather.App
import com.myapps.weather.R
import com.myapps.weather.data.network.DailyWeather
import com.myapps.weather.utils.setWeatherIcon
import com.myapps.weather.utils.Symbols.CELSIUS_DEGREE_SYMBOL
import com.myapps.weather.utils.Symbols.PLUS

class DailyWeatherAdapter : RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder>() {

    private var dailyWeatherList = ArrayList<DailyWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.daily_weather,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) dailyWeatherList[0].date =
            App.INSTANCE.applicationContext.getString(R.string.tomorrow)
        holder.onBind(dailyWeatherList[position])
    }

    fun updateForecastByTime(updDailyWeatherList: ArrayList<DailyWeather>) {
        dailyWeatherList.clear()
        dailyWeatherList.addAll(updDailyWeatherList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val weatherView: View) : RecyclerView.ViewHolder(weatherView) {
        fun onBind(dailyWeather: DailyWeather) {
            val date = itemView.findViewById<TextView>(R.id.dailyDate)
            date.text = dailyWeather.date

            val dayOfWeek = weatherView.findViewById<TextView>(R.id.dayOfWeek)
            dayOfWeek.text = dailyWeather.dayOfWeek

            val dayTemp = weatherView.findViewById<TextView>(R.id.dayTemp)
            val dTemp = dailyWeather.dayTemp
            dayTemp.text =
                if (dTemp > 0) "$PLUS$dTemp$CELSIUS_DEGREE_SYMBOL" else "$dTemp$CELSIUS_DEGREE_SYMBOL"

            val nightTemp = weatherView.findViewById<TextView>(R.id.nightTemp)
            val nTemp = dailyWeather.nightTemp
            nightTemp.text =
                if (nTemp > 0) "$PLUS$nTemp$CELSIUS_DEGREE_SYMBOL" else "$nTemp$CELSIUS_DEGREE_SYMBOL"

            val weatherIcon = weatherView.findViewById<ImageView>(R.id.dailyWeatherIcon)
            weatherIcon.setWeatherIcon(dailyWeather.icon)
        }
    }

}