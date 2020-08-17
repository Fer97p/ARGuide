package com.example.arguide.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arguide.R
import com.example.arguide.entities.Constants.Location.REQUEST_LOCATION

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_LOCATION) {
            mainActivityViewModel.processAction(
                MainActivityViewAction.IsGPSEnabled(
                    resultCode == Activity.RESULT_OK
                )
            )
        }
        super.onActivityResult(requestCode, resultCode, data)
    }*/
}
