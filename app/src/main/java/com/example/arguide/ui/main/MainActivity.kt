package com.example.arguide.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arguide.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    companion object {
        const val LOCATION_SETTING_REQUEST = 999
    }
}
