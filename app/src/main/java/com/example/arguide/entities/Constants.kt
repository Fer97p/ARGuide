package com.example.arguide.entities

import android.Manifest

object Constants {

    object ViewAnimation {
        const val MIN_ALPHA = 0.0f
        const val MAX_ALPHA = 0.8f
        const val FULL_ALPHA = 1.0f
        const val TIME = 500L
    }



    object Location {
        const val INTERVAL = 1000L
        const val FASTEST_INTERVAL = 1000L
        const val DEFAULT_ZOOM = 14.0f
        const val REQUEST_LOCATION = 0
        const val LOCATION_PERMISSION_REQUEST = 1

        const val STROKE_WIDTH = 10f
    }

    object Time {
        const val SECOND = 1000L
    }

    val PERMISSIONS = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
}
