package com.myapps.weather.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.ResolvableApiException
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.myapps.weather.App
import com.myapps.weather.R
import com.myapps.weather.ui.fragments.LocationOffFragment
import com.myapps.weather.ui.fragments.NetworkOffFragment
import com.myapps.weather.ui.fragments.WeatherFragment
import com.myapps.weather.utils.checkLocationPermission
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.lang.Exception
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainActivityView, PermissionListener {

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter = mainPresenter

    private val locationOffFragment = LocationOffFragment()
    private val networkOffFragment = NetworkOffFragment()
    private val weatherFragment = WeatherFragment()

    init {
        App.INSTANCE.appComponent.inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.stopLocationUpdates()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS || requestCode == REQUEST_PERMISSION) {
            if (resultCode == Activity.RESULT_OK) {
                if (checkLocationPermission()) {
                    mainPresenter.requestLocation()
                } else {
                    showLocationError()
                }
            } else {
                showLocationError()
            }
        }
    }

    override fun startResolutionForResult(exception: Exception) {
        if (exception is ResolvableApiException) {
            try {
                exception.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
            } catch (e: IntentSender.SendIntentException) {

            }
        }
    }

    override fun showForecast(lat: String, lon: String) {
        weatherFragment.apply {
            arguments = Bundle().apply {
                putString("latitude", lat)
                putString("longitude", lon)
            }
        }
        executeTransaction(weatherFragment)
    }

    override fun showLocationError() {
        executeTransaction(locationOffFragment)
    }

    override fun showNetworkError() {
        executeTransaction(networkOffFragment)
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        mainPresenter.requestLocation()
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        p1?.continuePermissionRequest()
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        executeTransaction(LocationOffFragment())
        if (p0?.isPermanentlyDenied!!) {
            showSettingsDialog()
        }
    }

    override fun checkPermission() {
        Dexter.withContext(this)
            .withPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(this)
            .check()
    }

    private fun showSettingsDialog() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.titleDialogSet))
            setMessage(getString(R.string.messageDialogSet))
            setPositiveButton(getString(R.string.posButDialogSet)) { dialog, _ ->
                dialog.cancel()
                openSettings()
            }
            setNegativeButton(getString(R.string.negButDialogSet)) { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, REQUEST_PERMISSION)
    }

    private fun executeTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    companion object {
        const val REQUEST_CHECK_SETTINGS = 1
        const val REQUEST_PERMISSION = 2
    }
}