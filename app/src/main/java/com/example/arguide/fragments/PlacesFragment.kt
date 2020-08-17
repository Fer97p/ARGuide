package com.example.arguide.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.arguide.R
import com.example.arguide.entities.Place
import com.example.arguide.main.PlaceAdapter
import com.google.android.gms.maps.model.LatLng

class PlacesFragment : Fragment(), PlaceAdapter.OnClickListener {
    override fun onClick(place: Place) {
        val action = PlacesFragmentDirections.actionPlacesFragmentToDetailsFragment(place.id, place.location.latitude.toFloat(), place.location.longitude.toFloat())
        findNavController().navigate(action)
    }

    private lateinit var viewModel: PlacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.places_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlacesViewModel::class.java)
        // TODO: Use the ViewModel
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recycler)
        recyclerView.layoutManager= LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val places = ArrayList<Place>()
        places.add(Place("Angustias",getString(R.string.antiguaId) ,getString(R.string.antiguaInfo) , R.drawable.angustias, LatLng(41.653490, -4.722820)))
        places.add(Place("Teatro", getString(R.string.antiguaId) ,getString(R.string.antiguaInfo) , R.drawable.teatro, LatLng(41.653490, -4.722820)))
        places.add(Place("Antigua", getString(R.string.antiguaId) ,getString(R.string.antiguaInfo) , R.drawable.antigua, LatLng(41.653490, -4.722820)))

        val adapter = PlaceAdapter(places, this)
        recyclerView.adapter = adapter
    }


}
