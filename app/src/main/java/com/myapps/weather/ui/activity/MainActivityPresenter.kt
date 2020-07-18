package com.myapps.weather.ui.activity

import android.app.Application
import android.os.Looper
import com.google.android.gms.location.*
import com.myapps.weather.utils.checkLocationPermission
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject constructor(private val application: Application) :
    MvpPresenter<MainActivityView>() {

    private val fusedLocationService = LocationServices.getFusedLocationProviderClient(application)
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest

    fun requestLocation() {
        requestLocationSettingsAndStartUpdates()
    }

    fun stopLocationUpdates() {
        fusedLocationService.removeLocationUpdates(locationCallback)
    }

    private fun requestLocationSettingsAndStartUpdates() {
        createLocationRequest()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()
        val client = LocationServices.getSettingsClient(application)

        val task = client.checkLocationSettings(builder)
        task.addOnSuccessListener { startLocationUpdates() }
        task.addOnFailureListener { exception: Exception ->
            viewState.startResolutionForResult(exception)
        }
    }

    private fun startLocationUpdates() {
        createLocationCallback()
        if (checkLocationPermission()) {
            fusedLocationService.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval = 1
            numUpdates = 1
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }
    }

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                result ?: return
                viewState.showForecast(
                    result.locations[0].latitude.toString(),
                    result.locations[0].longitude.toString()
                )
            }
        }
    }
}