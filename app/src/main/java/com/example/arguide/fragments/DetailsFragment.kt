package com.example.arguide.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs

import com.example.arguide.R
import com.example.arguide.main.IntermediateActivity
import java.lang.ref.PhantomReference

class DetailsFragment : Fragment() {

    private var reference: String = ""
    private lateinit var textContainer : TextView
    private lateinit var button: Button
    private lateinit var viewModel: DetailsViewModel
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        reference = args.placeReference
        textContainer = requireView().findViewById(R.id.text_container)
        textContainer.text = reference
        button = requireView().findViewById(R.id.goToCamera)
        button.setOnClickListener{
            val intent = Intent(activity, IntermediateActivity::class.java)
            startActivity(intent)
        }
        button = requireView().findViewById(R.id.button_check)
        button.setOnClickListener{
            Toast.makeText(activity, "Parámetro transferido: $reference", Toast.LENGTH_SHORT).show()
        }
    }
}
