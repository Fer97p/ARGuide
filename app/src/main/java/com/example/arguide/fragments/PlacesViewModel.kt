package com.example.arguide.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlacesViewModel : ViewModel() {
    val place = MutableLiveData<Any>()

    fun setPlace(place : String){
        this.place.setValue(place)
    }
}
