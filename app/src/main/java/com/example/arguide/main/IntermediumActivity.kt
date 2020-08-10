package com.example.arguide.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.arguide.R
import com.unity3d.player.UnityPlayerActivity

class IntermediumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermedium)
        /*val intent = Intent(this, UnityPlayerActivity::class.java)
        startActivity(intent)*/
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this, UnityPlayerActivity::class.java)
            startActivity(intent)
        }
    }
}