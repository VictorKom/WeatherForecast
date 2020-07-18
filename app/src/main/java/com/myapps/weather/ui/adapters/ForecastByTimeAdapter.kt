package com.myapps.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapps.weather.R
import com.myapps.weather.data.network.WeatherByTime
import com.myapps.weather.utils.setWeatherIcon
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.myapps.weather.utils.Symbols.CELSIUS_DEGREE_SYMBOL
import com.myapps.weather.utils.Symbols.PLUS

class ForecastByTimeAdapter : RecyclerView.Adapter<ForecastByTimeAdapter.ViewHolder>() {

    private val forecastByTimeList = ArrayList<WeatherByTime>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.weather_by_time,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(forecastByTimeList[position])
    }

    override fun getItemCount(): Int {
        return forecastByTimeList.size
    }

    fun updateForecastByTime(updForecastByTime: ArrayList<WeatherByTime>) {
        forecastByTimeList.clear()
        forecastByTimeList.addAll(updForecastByTime)
        notifyDataSetChanged()
    }

    class ViewHolder(private val weatherView: View) : RecyclerView.ViewHolder(weatherView) {

        fun onBind(weather: WeatherByTime) {
            val date =
                SimpleDateFormat("y-MM-d HH:mm:ss", Locale.getDefault()).parse(weather.dt_txt)
            val dateStr = date?.let { SimpleDateFormat("d MMMM", Locale.getDefault()).format(it) }
            val timeStr = date?.let { SimpleDateFormat("HH:mm", Locale.getDefault()).format(it) }

            val dateView = weatherView.findViewById<TextView>(R.id.date)
            val timeView = weatherView.findViewById<TextView>(R.id.time)

            timeView.text = timeStr
            dateView.text = if (timeStr == "00:00") dateStr else ""

            val temperatureView = weatherView.findViewById<TextView>(R.id.timeTemperature)
            val temp = weather.main.temp.toInt()
            val tempStr =
                if (temp > 0) "$PLUS$temp$CELSIUS_DEGREE_SYMBOL" else "$temp$CELSIUS_DEGREE_SYMBOL"
            temperatureView.text = tempStr

            val weatherIcon = weatherView.findViewById<ImageView>(R.id.timeWeatherIcon)
            weatherIcon.setWeatherIcon(weather.weather[0].icon)
        }
    }
}