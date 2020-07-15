package com.myapps.weather.utils

import android.Manifest
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.myapps.weather.App
import com.squareup.picasso.Picasso

fun ViewGroup.inflate (layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

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