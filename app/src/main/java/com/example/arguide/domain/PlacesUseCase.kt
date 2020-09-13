package com.example.arguide.domain

import com.example.arguide.data.PlacesDataSet
import com.example.arguide.ui.entities.Place

class PlacesUseCase {
    private val placesDataSet = PlacesDataSet()
    fun getListaLugares() : List<Place>{
        return placesDataSet.createDataSet()
    }
}