package com.example.arguide.data

import android.provider.Settings.Global.getString
import com.example.arguide.R
import com.example.arguide.ui.entities.Place
import com.google.android.gms.maps.model.LatLng

class PlacesDataSet {
    fun createDataSet() : List<Place>{
        return listOf(Place(
            "Antigua",
            "antiguaIc",
            "antiguaInfo",
            R.drawable.antigua_slider2,
            LatLng(41.653490, -4.722820),
            0
        ))
    }
}