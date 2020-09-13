package com.example.arguide.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denzcoskun.imageslider.models.SlideModel
import com.example.arguide.domain.DetailsUseCase

class DetailsViewModel : ViewModel() {
    private val detailsUseCase = DetailsUseCase()
    private val listImages = MutableLiveData<ArrayList<SlideModel>>()

    private fun setListData(list : ArrayList<SlideModel>){
        listImages.value = list
    }
    fun getListData(name: String){
        setListData(detailsUseCase.getImageCollection(name))
    }
    fun getListLiveData(): LiveData<ArrayList<SlideModel>> {
        return listImages
    }
}