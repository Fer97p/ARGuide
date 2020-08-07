package com.example.arguide.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arguide.R
import com.example.arguide.entities.Place

class PlaceAdapter(var list: ArrayList<Place>):RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bindItems(data: Place){
            val name: TextView = itemView.findViewById(R.id.title)
            val distancia: TextView = itemView.findViewById(R.id.distancia)
            val image: ImageView = itemView.findViewById(R.id.imageView_1)

            name.text=data.name
            distancia.text=data.distancia.toString()
            Glide.with(itemView.context).load(data.image).into(image)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "Has hecho click en ${data.name}", Toast.LENGTH_LONG).show()
            }
        }
    }
}