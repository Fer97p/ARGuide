package com.example.arguide.ui.details

import android.content.Intent
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.arguide.R
import com.example.arguide.ui.main.IntermediateActivity
import com.example.arguide.ui.main.MainActivity
import com.example.arguide.viewmodel.DetailsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var tripButton: FloatingActionButton
    private lateinit var cameraButton: FloatingActionButton
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tripButton = requireView().findViewById(R.id.trip)
        cameraButton = requireView().findViewById(R.id.cameraAction)
        tripButton.setOnClickListener {
            val action =
                DetailsFragmentDirections.actionDetailsFragmentToMapsFragment(
                    args.place,
                    args.placeLat,
                    args.placeLong,
                    args.placeName
                )
            findNavController().navigate(action)
        }
        cameraButton.setOnClickListener {
            val intent = Intent(activity, IntermediateActivity::class.java)
            startActivity(intent)
        }
        (activity as MainActivity).supportActionBar?.title = args.placeName
        val detailsObserver = Observer<ArrayList<SlideModel>> {
            val imageSlider = requireView().findViewById<ImageSlider>(R.id.image_slider)
            imageSlider.setImageList(it, ScaleTypes.CENTER_CROP)
            imageSlider.stopSliding()
        }
        viewModel.getListLiveData().observe(viewLifecycleOwner, detailsObserver)
        viewModel.getListData(args.place)
        val textContainer: TextView = requireView().findViewById(R.id.textInfo)
        textContainer.text = args.placeDescription
        textContainer.movementMethod = ScrollingMovementMethod()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textContainer.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
    }
}
