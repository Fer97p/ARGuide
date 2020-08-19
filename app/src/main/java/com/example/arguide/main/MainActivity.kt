package com.example.arguide.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.arguide.R
import com.example.arguide.entities.Constants.Location.REQUEST_LOCATION
import com.example.arguide.fragments.MapsFragment
import com.google.android.gms.maps.MapFragment
import kotlinx.android.synthetic.main.fragment_maps.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    companion object {
        const val LOCATION_SETTING_REQUEST = 999
    }
}
