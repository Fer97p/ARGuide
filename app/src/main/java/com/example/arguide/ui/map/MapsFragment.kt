package com.example.arguide.ui.map

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Rect
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.navArgs
import com.example.arguide.R
import com.example.arguide.data.Constants.Location.DEFAULT_ZOOM
import com.example.arguide.data.Constants.Location.FASTEST_INTERVAL
import com.example.arguide.data.Constants.Location.INTERVAL
import com.example.arguide.data.Constants.Location.LOCATION_PERMISSION_REQUEST
import com.example.arguide.data.Constants.Location.REQUEST_LOCATION
import com.example.arguide.data.Constants.PERMISSIONS
import com.example.arguide.data.GoogleMapDTO
import com.example.arguide.ui.main.IntermediateActivity
import com.example.arguide.ui.details.DetailsFragmentArgs
import com.example.arguide.ui.main.MainActivity
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_maps.*
import okhttp3.OkHttpClient
import okhttp3.Request


class MapsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val mapLayout by lazy { childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment }
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var mMap: GoogleMap

    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationManager: LocationManager
    private var origin: Location? = null
    private lateinit var originLatLng: LatLng
    private lateinit var destination: LatLng
    private var distance: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = args.placeName
        destination = LatLng(args.placeLat.toDouble(), args.placeLong.toDouble())
        val button = requireView().findViewById<FloatingActionButton>(R.id.cameraAction)
        button.setOnClickListener {
            val intent = Intent(activity, IntermediateActivity::class.java)
            startActivity(intent)
        }
        cameraAction.visibility = View.GONE
        if (!isNetworkAvailable()) {
            handleNetworkEnabled(false)
        } else {
            mapLayout.getMapAsync { googleMap ->
                mMap = googleMap
                requestPermissions(
                    PERMISSIONS.toTypedArray(),
                    LOCATION_PERMISSION_REQUEST
                )
            }
        }
    }
    private fun enableGPS() {
        if (!::googleApiClient.isInitialized) {
            googleApiClient = GoogleApiClient.Builder(requireContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                    override fun onConnected(p0: Bundle?) {}
                    override fun onConnectionSuspended(p0: Int) {
                        googleApiClient.connect()
                    }
                })
                .addOnConnectionFailedListener {
                    Log.d(
                        "MapsFragment",
                        "Error ${it.errorMessage}"
                    )//To change body of created functions use File | Settings | File Templates.
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

        @Suppress("DEPRECATION") val result =
            LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback {
            try {
                //startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                this.startIntentSenderForResult(
                    it.status.resolution.intentSender,
                    REQUEST_LOCATION,
                    null,
                    0,
                    0,
                    0,
                    null
                )
                //it.status.startResolutionForResult(requireActivity(), REQUEST_LOCATION)
            } catch (e: IntentSender.SendIntentException) {
                Log.d("MapFragment", "Error $e")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                if (context?.let {
                        ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    } != PackageManager.PERMISSION_GRANTED && context?.let {
                        ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    } != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
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
                rlp.topMargin =
                    statusBarHeight + rlp.topMargin + resources.getDimension(R.dimen.medium_margin)
                        .toInt()

                locationManager =
                    requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    setupLocationManager()
                    getLocation()

                    if (origin != null) {
                        val cameraUpdate = CameraUpdateFactory
                            .newLatLngZoom(
                                LatLng(
                                    origin!!.latitude,
                                    origin!!.longitude
                                ),
                                DEFAULT_ZOOM
                            )
                        mMap.animateCamera(cameraUpdate, object : GoogleMap.CancelableCallback {
                            override fun onFinish() {
                                //if(distance in 1..9){
                                cameraAction.visibility = View.VISIBLE
                                //}
                            }

                            override fun onCancel() {
                                //if(distance in 1..9){
                                cameraAction.visibility = View.VISIBLE
                                //}
                            }
                        })

                        val url = getUrl(originLatLng, destination)
                        GetDirection(url).execute()
                    }


                } else {
                    enableGPS()
                }

            } else {
                showMessage(
                    getString(R.string.denied_location_permission), getString(R.string.activate)
                ) {
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
                val locationAux = Location("")
                locationAux.latitude = destination.latitude
                locationAux.longitude = destination.longitude
                distance = locationResult.lastLocation.distanceTo(locationAux).toInt()
                if (distance < 10) {
                    cameraAction.visibility = View.VISIBLE
                }/*else{
                    if(cameraAction!=null){
                        cameraAction.visibility = View.GONE
                    }
                }*/
            }
        }

        locationProviderClient = FusedLocationProviderClient(requireContext())
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun getLocation() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        origin = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        while (origin == null) {
            origin = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        }
        if (origin != null) {
            originLatLng = LatLng(origin!!.latitude, origin!!.longitude)
        }

        mMap.addMarker(MarkerOptions().position(destination).title(args.placeName))

    }

    private fun getUrl(from: LatLng, to: LatLng): String {
        val origin = "origin=" + from.latitude + "," + from.longitude
        val dest = "destination=" + to.latitude + "," + to.longitude
        val sensor = "sensor=false"
        val params = "$origin&$dest&$sensor"
        return "https://maps.googleapis.com/maps/api/directions/json?$params&key="+getString(R.string.google_maps_key)

    }

    private inner class GetDirection(val url: String) :
        AsyncTask<Void, Void, List<List<LatLng>>>() {
        override fun doInBackground(vararg params: Void?): List<List<LatLng>> {
            //Log.d("urlcheck", "url: " + url)
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val data = response.body!!.string()
            //Log.d("GoogleMap", " data : $data")
            val result = ArrayList<List<LatLng>>()
            try {
                val respObj = Gson().fromJson(data, GoogleMapDTO::class.java)

                val path = ArrayList<LatLng>()

                for (i in 0 until respObj.routes[0].legs[0].steps.size) {
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: List<List<LatLng>>) {
            val lineoption = PolylineOptions()
            for (i in result.indices) {
                lineoption.addAll(result[i])
                lineoption.width(10f)
                lineoption.color(Color.BLUE)
                lineoption.geodesic(true)
            }
            mMap.addPolyline(lineoption)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_LOCATION) {
            try {
                super.onActivityResult(requestCode, resultCode, data)

                if (resultCode == RESULT_OK) {
                    handleGpsEnabled(
                        resultCode == RESULT_OK
                    )
                } else {
                    handleGpsEnabled(
                        resultCode == RESULT_OK
                    )
                }


            } catch (e: Exception) {
                Log.e("MapsFragment", e.toString())
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }

    private fun handleNetworkEnabled(success: Boolean) {
        if (!success) {
            showMessage(
                "Si no activa la red, no podrá obtener la ruta a su destino",
                "",
                ::enableGPS
            )
        }
    }

    private fun handleGpsEnabled(success: Boolean) {
        if (!success) {
            showMessage(
                getString(R.string.denied_activation_location),
                getString(R.string.grant),
                ::enableGPS
            )
        } else {
            if (!isNetworkAvailable()) {
                handleNetworkEnabled(false)
            } else {
                mapLayout.getMapAsync { googleMap ->
                    mMap = googleMap
                    requestPermissions(
                        PERMISSIONS.toTypedArray(),
                        LOCATION_PERMISSION_REQUEST
                    )
                }
                locationManager =
                    requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
                setupLocationManager()
                getLocation()

                if (origin != null) {
                    val cameraUpdate = CameraUpdateFactory
                        .newLatLngZoom(
                            LatLng(
                                origin!!.latitude,
                                origin!!.longitude
                            ),
                            DEFAULT_ZOOM
                        )

                    mMap.animateCamera(cameraUpdate, object : GoogleMap.CancelableCallback {
                        override fun onFinish() {
                            //if(distance in 1..9){
                            cameraAction.visibility = View.VISIBLE
                            //}
                        }

                        override fun onCancel() {
                            //if(distance in 1..9){
                            cameraAction.visibility = View.VISIBLE
                            //}
                        }
                    })

                    val url = getUrl(originLatLng, destination)
                    GetDirection(url).execute()
                }
            }
        }
    }

    private fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(
                lat.toDouble() / 1E5,
                lng.toDouble() / 1E5
            )
            poly.add(p)
        }

        return poly
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