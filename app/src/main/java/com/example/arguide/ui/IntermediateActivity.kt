package com.example.arguide.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arguide.R
import com.unity3d.player.UnityPlayerActivity

class IntermediateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermediate)
        /*val intent = Intent(this, UnityPlayerActivity::class.java)
        startActivity(intent)*/
        val intent = Intent(this, UnityPlayerActivity::class.java)
        startActivity(intent)
    }
}