package com.example.arguide.data

import android.Manifest

object Constants {
    sealed class Views {
        object Home: Views()
        object Trip: Views()
        object History: Views()
        object Points: Views()
        object Settings: Views()
        object Instructions: Views()
    }

    object Location {
        const val INTERVAL = 1000L
        const val FASTEST_INTERVAL = 1000L
        const val DEFAULT_ZOOM = 14.5f
        const val REQUEST_LOCATION = 0
        const val LOCATION_PERMISSION_REQUEST = 1
    }

    val PERMISSIONS = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
}
