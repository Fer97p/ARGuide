package com.example.arguide.entities

import com.google.android.gms.maps.model.LatLng

data class Place (var id: String, var name: String, var description: String, var image: Int, var location: LatLng, var distance: Int){
}