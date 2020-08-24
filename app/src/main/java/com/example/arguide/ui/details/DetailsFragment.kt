package com.example.arguide.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

import com.example.arguide.R
import com.example.arguide.ui.DetailsFragmentArgs
import com.example.arguide.ui.DetailsFragmentDirections
import com.example.arguide.ui.main.MainActivity

class DetailsFragment : Fragment() {

    private var areImagesAdded = false
    private lateinit var button: Button
    private val args: DetailsFragmentArgs by navArgs()
    private var sliderItems = ArrayList<SlideModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(!areImagesAdded){
            addContent(args.place)
        }
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        button = requireView().findViewById(R.id.trip)
        button.setOnClickListener{
            val action =
                DetailsFragmentDirections.actionDetailsFragmentToMapsFragment(
                    args.place,
                    args.placeLat,
                    args.placeLong
                )
            findNavController().navigate(action)
        }
        val imageSlider = requireView().findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(sliderItems, ScaleTypes.CENTER_CROP)
        imageSlider.stopSliding()
        val textContainer : TextView = requireView().findViewById(R.id.textInfo)
        if(args.place=="Antigua") {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.antiguaId)
            textContainer.text = getString(R.string.antiguaInfo)
        }
        if(args.place=="Angustias") {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.angustiasId)
            textContainer.text = getString(R.string.angustiasInfo)
        }
        if(args.place=="Teatro") {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.teatroId)
            textContainer.text = getString(R.string.teatroInfo)
        }

    }
    private fun addContent(place : String){
        if(place=="Antigua") {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.antiguaId)
            sliderItems.add(SlideModel(R.drawable.antigua_slider2, "Vista exterior de la iglesia"))
            sliderItems.add(SlideModel(R.drawable.antigua_slider1, "Interior de la iglesia"))
            sliderItems.add(SlideModel(R.drawable.antigua_slider3, "Imagen antigua antes de la restauración"))
        }
        if(args.place=="Angustias") {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.angustiasId)
            sliderItems.add(SlideModel(R.drawable.angustias_slider1, "Vista de la fachada de la iglesia"))
            sliderItems.add(SlideModel(R.drawable.angustias_slider2, "Imagen de la iglesia de principios del siglo XX"))
            sliderItems.add(SlideModel(R.drawable.angustias_slider3, "Interior de la iglesia durante Semana Santa"))
        }
        if(args.place=="Teatro") {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.teatroId)
            sliderItems.add(SlideModel(R.drawable.teatro_slider1, "Primitivo teatro construido en 1609"))
            sliderItems.add(SlideModel(R.drawable.teatro_slider2, "Fachada del teatro en 1907"))
            sliderItems.add(SlideModel(R.drawable.teatro_slider3, "Actual edificio de viviendas levantado en el solar"))
            sliderItems.add(SlideModel(R.drawable.teatro_slider4, "Boceto de la fachada que no llegó a realizarse"))
        }
        areImagesAdded = true
    }
}
