package com.example.arguide.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.arguide.R
import com.example.arguide.entities.Place
import com.example.arguide.main.PlaceAdapter

class PlacesFragment : Fragment() {

    companion object {
        fun newInstance() = PlacesFragment()
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

        places.add(Place("Antigua", 2000, R.drawable.angustias))
        places.add(Place("Teatro", 230, R.drawable.teatro))

        val adapter = PlaceAdapter(places)

        recyclerView.adapter = adapter
    }

}
