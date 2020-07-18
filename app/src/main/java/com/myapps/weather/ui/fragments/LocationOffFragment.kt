package com.myapps.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myapps.weather.R
import com.myapps.weather.ui.activity.MainActivityView
import kotlinx.android.synthetic.main.fragment_location_off.*

class LocationOffFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_off, container, false)
    }

    override fun onStart() {
        super.onStart()
        updateRequest.setOnClickListener { (activity as MainActivityView).checkPermission() }
    }
}