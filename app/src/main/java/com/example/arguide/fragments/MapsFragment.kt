package com.example.arguide.fragments

import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Rect
import android.location.Location
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.fragment.navArgs
import com.example.arguide.R
import com.example.arguide.entities.Constants
import com.example.arguide.entities.Constants.Location.DEFAULT_ZOOM
import com.example.arguide.entities.Constants.Location.FASTEST_INTERVAL
import com.example.arguide.entities.Constants.Location.INTERVAL
import com.example.arguide.entities.Constants.Location.LOCATION_PERMISSION_REQUEST
import com.example.arguide.entities.Constants.Location.REQUEST_LOCATION
import com.example.arguide.entities.Constants.PERMISSIONS
import com.example.arguide.entities.Place
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar


class MapsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val mapLayout by lazy { childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment}
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var mMap: GoogleMap
    //private val mainActivityViewModel: MainActivityViewModel by sharedViewModel()
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationManager: LocationManager
    private lateinit var origin : Location
    private lateinit var originLatLng : LatLng

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val destination = LatLng(args.placeLat.toDouble(), args.placeLong.toDouble())
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.isMyLocationEnabled = true
        mMap.addMarker(MarkerOptions().position(destination).title("Marker in ${args.place}"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(destination))


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        //observe(mainActivityViewModel.gpsEnabled, ::handleGpsEnabled)
        mapLayout.getMapAsync { googleMap ->
            mMap = googleMap
            requestPermissions(
                PERMISSIONS.toTypedArray(),
                LOCATION_PERMISSION_REQUEST
            )
        }
        mapFragment?.getMapAsync(callback)
    }
    private fun enableGPS() {
        if (!::googleApiClient.isInitialized) {
            googleApiClient = GoogleApiClient.Builder(requireContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(object: GoogleApiClient.ConnectionCallbacks {
                    override fun onConnected(p0: Bundle?) {}
                    override fun onConnectionSuspended(p0: Int) {
                        googleApiClient.connect()
                    }
                })
                .addOnConnectionFailedListener {
                    Log.d("TripFragment", "Error ${it.errorMessage}")//To change body of created functions use File | Settings | File Templates.
                }
                .build()
            googleApiClient.connect()
        }

        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = INTERVAL
        locationRequest.fastestInterval = FASTEST_INTERVAL

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)

        val result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback {
            try {
                it.status.startResolutionForResult(requireActivity(), REQUEST_LOCATION)
            } catch (e: IntentSender.SendIntentException) {
                Log.d("TripFragment", "Error $e")
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isCompassEnabled = false

                val locationButton =
                    (mapLayout.view?.findViewById<View>(Integer.parseInt("1"))?.parent as View).findViewById<View>(
                        Integer.parseInt("2")
                    )

                val rectangle = Rect()
                requireActivity().window.decorView.getWindowVisibleDisplayFrame(rectangle)
                val statusBarHeight = rectangle.top

                val rlp = locationButton.layoutParams as (RelativeLayout.LayoutParams)
                rlp.topMargin = statusBarHeight + rlp.topMargin + resources.getDimension(R.dimen.medium_margin).toInt()

                locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    setupLocationManager()
                    getLastKnownLocation()

                } else {
                    enableGPS()
                }

            } else {
                showMessage(
                    getString(R.string.denied_location_permission), getString(R.string.activate)) {
                    requestPermissions(
                        PERMISSIONS.toTypedArray(),
                        LOCATION_PERMISSION_REQUEST
                    )
                }
            }
        }
    }
    private fun setupLocationManager() {
        if (!::locationRequest.isInitialized) {
            locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = INTERVAL
            locationRequest.fastestInterval = FASTEST_INTERVAL
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val cameraUpdate = CameraUpdateFactory
                    .newLatLngZoom(
                        LatLng(
                            locationResult.lastLocation.latitude,
                            locationResult.lastLocation.longitude
                        ),
                        DEFAULT_ZOOM
                    )

                mMap.animateCamera(cameraUpdate, object: GoogleMap.CancelableCallback {
                    override fun onFinish() {
                        //startTrip.visibility = View.VISIBLE
                        //cleanMap.visibility = View.VISIBLE
                    }
                    override fun onCancel() {
                        //startTrip.visibility = View.VISIBLE
                        //cleanMap.visibility = View.VISIBLE
                    }
                })
                locationProviderClient.removeLocationUpdates(this)
            }
        }

        locationProviderClient = FusedLocationProviderClient(requireContext())
        locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }
    private fun getLastKnownLocation(){
        origin = locationManager.getLastKnownLocation(locationManager.allProviders[0])
        originLatLng = LatLng(origin.latitude, origin.longitude)
        mMap.addMarker(MarkerOptions().position(originLatLng).title("Marker in my location"))
    }
    private fun handleGpsEnabled(success: Boolean) {
        if (!success) {
            showMessage(getString(R.string.denied_activation_location), getString(R.string.grant), ::enableGPS)
        } else {
            setupLocationManager()
        }
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