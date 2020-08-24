package com.example.arguide.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arguide.domain.PlacesUseCase
import com.example.arguide.ui.entities.Place

class PlaceViewModel : ViewModel() {

    private val placesUseCase = PlacesUseCase()
    private val listPlaces = MutableLiveData<List<Place>>()

    private fun setListData(list : List<Place>){
        listPlaces.value = list
    }
    fun getListData(){
        setListData(placesUseCase.getListaLugares())
    }
    fun getListLiveData():LiveData<List<Place>>{
        return listPlaces
    }
}