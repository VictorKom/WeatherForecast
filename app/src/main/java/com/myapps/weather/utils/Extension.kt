package com.myapps.weather.utils

import android.Manifest
import android.content.pm.PackageManager
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.myapps.weather.App
import com.squareup.picasso.Picasso

fun ImageView.setWeatherIcon (iconName: String) {
    val url = "https://openweathermap.org/img/wn/$iconName@2x.png"
    Picasso.get().load(url).into(this)
}

fun checkLocationPermission(): Boolean {
    return ActivityCompat.checkSelfPermission(
        App.INSTANCE,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}