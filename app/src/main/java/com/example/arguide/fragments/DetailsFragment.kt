package com.example.arguide.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

import com.example.arguide.R
import com.example.arguide.entities.Place
import com.example.arguide.main.IntermediateActivity

class DetailsFragment : Fragment() {

    private lateinit var button: Button
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        var imageSlider = requireView().findViewById<ImageSlider>(R.id.image_slider)
        var textContainer : TextView = requireView().findViewById(R.id.textInfo)

        var sliderItems = ArrayList<SlideModel>()
        if(args.place=="Antigua") {
            sliderItems.add(SlideModel(R.drawable.antigua_slider2, "Vista exterior de la iglesia"))
            sliderItems.add(SlideModel(R.drawable.antigua_slider1, "Interior de la iglesia"))
            sliderItems.add(SlideModel(R.drawable.antigua_slider3, "Imagen antigua antes de la restauración"))
            textContainer.text = getString(R.string.antiguaInfo)
        }

        imageSlider.setImageList(sliderItems, ScaleTypes.CENTER_CROP)
        imageSlider.stopSliding()
        button = requireView().findViewById(R.id.trip)
        button.setOnClickListener{
            val action = DetailsFragmentDirections.actionDetailsFragmentToMapsFragment(args.place, args.placeLat, args.placeLong)
            findNavController().navigate(action)
        }
        /*button = requireView().findViewById(R.id.button_check)
        button.setOnClickListener{
            val intent = Intent(activity, IntermediateActivity::class.java)
            startActivity(intent)
        }
        }*/

    }
}
