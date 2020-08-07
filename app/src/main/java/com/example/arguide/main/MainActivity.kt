package com.example.arguide.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arguide.R
import com.example.arguide.entities.Place

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView:RecyclerView=findViewById(R.id.recycler)
        recyclerView.layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val places = ArrayList<Place>()

        places.add(Place("Antigua", 2000, R.drawable.angustias))
        places.add(Place("Teatro", 230, R.drawable.teatro))

        val adapter = PlaceAdapter(places)

        recyclerView.adapter = adapter

    }
}
