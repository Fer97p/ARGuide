package com.example.arguide.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arguide.R
import com.example.arguide.entities.Place
import com.example.arguide.fragments.DetailsFragment
import com.example.arguide.interfaces.Communicator

class MainActivity : AppCompatActivity(), Communicator{
    override fun passData(data: String) {
        val bundle = Bundle()
        bundle.putString("input_txt", data)
        val det_fragment = DetailsFragment()
        det_fragment.arguments = bundle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
