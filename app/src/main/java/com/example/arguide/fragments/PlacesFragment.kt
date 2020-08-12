package com.example.arguide.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.arguide.R
import com.example.arguide.entities.Place
import com.example.arguide.interfaces.Communicator
import com.example.arguide.main.PlaceAdapter
import com.unity3d.player.UnityPlayerActivity

class PlacesFragment : Fragment(), PlaceAdapter.OnClickListener {
    lateinit var com : Communicator
    override fun onClick(place: Place) {
        //Toast.makeText(activity, "Has hecho click en ${place.name}, enhorabuena!", Toast.LENGTH_LONG).show()
        /*com = activity as Communicator
        com.passData(place.name)*/
        val action = PlacesFragmentDirections.actionPlacesFragmentToDetailsFragment(place.name)
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

        places.add(Place("Antigua", 2000, R.drawable.angustias))
        places.add(Place("Teatro", 230, R.drawable.teatro))

        val adapter = PlaceAdapter(places, this)

        recyclerView.adapter = adapter
    }


}
