package com.example.arguide.ui.places

import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
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
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar

class PlacesFragment : Fragment(), PlaceAdapter.OnClickListener {
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationManager: LocationManager
    private lateinit var origin: Location
    private lateinit var originLatLng: LatLng
    private var distance: Int = -1
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var places : ArrayList<Place>
    val viewModel : PlaceViewModel by viewModels()


    override fun onClick(place: Place) {
        val action =
            PlacesFragmentDirections.actionPlacesFragmentToDetailsFragment(
                place.id,
                place.location.latitude.toFloat(),
                place.location.longitude.toFloat()
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
        (activity as MainActivity).supportActionBar?.title = "Lugares"
        val placesObserver = Observer<List<Place>>{
            val adapter = PlaceAdapter(it, this)
            val recyclerView: RecyclerView = requireView().findViewById(R.id.recycler)
            recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            recyclerView.adapter = adapter
        }
        viewModel.getListLiveData().observe(viewLifecycleOwner, placesObserver)
        viewModel.getListData()

        /*places = ArrayList<Place>()
        places.add(
            Place(
                "Antigua",
                getString(R.string.antiguaId),
                getString(R.string.antiguaInfo),
                R.drawable.antigua_slider2,
                LatLng(41.653490, -4.722820),
                0
            )
        )
        places.add(
            Place(
                "Angustias",
                getString(R.string.angustiasId),
                getString(R.string.angustiasInfo),
                R.drawable.angustias_slider1,
                LatLng(41.654110, -4.723860),
                0
            )
        )
        places.add(
            Place(
                "Teatro",
                getString(R.string.teatroId),
                getString(R.string.teatroInfo),
                R.drawable.teatro,
                LatLng(41.652210, -4.730590),
                0
            )
        )*/

        /*locationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        for (place in places) {
            val locationAux = Location("")
            locationAux.latitude = place.location.latitude
            locationAux.longitude = place.location.longitude
            place.distance = origin.distanceTo(locationAux).toInt()
        }*/

    }

    private fun showMessage(message: String, titleButton: String, function: () -> Unit = {}) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).setAction(titleButton) {
            function()
        }
            .show()
    }


}
