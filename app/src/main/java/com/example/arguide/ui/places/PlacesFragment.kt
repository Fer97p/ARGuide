package com.example.arguide.ui.places


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.arguide.R
import com.example.arguide.ui.entities.Place
import com.example.arguide.ui.main.MainActivity
import com.example.arguide.viewmodel.PlaceViewModel

class PlacesFragment : Fragment(), PlaceAdapter.OnClickListener {
    private val viewModel: PlaceViewModel by viewModels()

    override fun onClick(place: Place) {
        val action =
            PlacesFragmentDirections.actionPlacesFragmentToDetailsFragment(
                place.id,
                place.location.latitude.toFloat(),
                place.location.longitude.toFloat(),
                place.name,
                place.description
            )
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.places_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.places_toolbar_title)
        val placesObserver = Observer<List<Place>> {
            val adapter = PlaceAdapter(it, this)
            val recyclerView: RecyclerView = requireView().findViewById(R.id.recycler)
            recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            recyclerView.adapter = adapter
        }
        viewModel.getListLiveData().observe(viewLifecycleOwner, placesObserver)
        viewModel.getListData()
    }

}
