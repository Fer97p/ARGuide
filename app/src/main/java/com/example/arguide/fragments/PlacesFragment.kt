package com.example.arguide.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Rect
import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.arguide.R
import com.example.arguide.entities.Constants
import com.example.arguide.entities.Constants.Location.FASTEST_INTERVAL
import com.example.arguide.entities.Constants.Location.INTERVAL
import com.example.arguide.entities.Constants.Location.LOCATION_PERMISSION_REQUEST
import com.example.arguide.entities.Constants.Location.REQUEST_LOCATION
import com.example.arguide.entities.Constants.PERMISSIONS
import com.example.arguide.entities.Place
import com.example.arguide.main.MainActivity
import com.example.arguide.main.PlaceAdapter
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_maps.*

class PlacesFragment : Fragment(), PlaceAdapter.OnClickListener {

    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationManager: LocationManager
    private lateinit var origin: Location
    private lateinit var originLatLng: LatLng
    private var distance: Int = -1
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var places : ArrayList<Place>


    override fun onClick(place: Place) {
        val action = PlacesFragmentDirections.actionPlacesFragmentToDetailsFragment(
            place.id,
            place.location.latitude.toFloat(),
            place.location.longitude.toFloat()
        )
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
        (activity as MainActivity).supportActionBar?.title = "Lugares"
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        places = ArrayList<Place>()
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
        )

        /*locationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        for (place in places) {
            val locationAux = Location("")
            locationAux.latitude = place.location.latitude
            locationAux.longitude = place.location.longitude
            place.distance = origin.distanceTo(locationAux).toInt()
        }*/
        val adapter = PlaceAdapter(places, this)
        recyclerView.adapter = adapter
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
