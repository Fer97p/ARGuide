package com.example.arguide.domain

import com.denzcoskun.imageslider.models.SlideModel
import com.example.arguide.data.DetailsDataSet

class DetailsUseCase {
    private val detailsDataSet = DetailsDataSet()
    fun getImageCollection(name: String) : ArrayList<SlideModel>{
        return detailsDataSet.createDataSet(name)
    }
}