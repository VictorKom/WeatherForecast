package com.myapps.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myapps.weather.R
import com.myapps.weather.ui.activities.MainActivityView
import com.myapps.weather.utils.inflate
import kotlinx.android.synthetic.main.fragment_location_off.*

class NetworkOffFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_network_off)
    }

    override fun onStart() {
        super.onStart()
        updateRequest.setOnClickListener { (activity as MainActivityView).checkPermission() }
    }
}