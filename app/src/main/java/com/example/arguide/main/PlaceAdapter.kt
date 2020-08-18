package com.example.arguide.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arguide.R
import com.example.arguide.entities.Place
import com.makeramen.roundedimageview.RoundedImageView
import org.jetbrains.anko.image

class PlaceAdapter(var list: ArrayList<Place>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
        val place = list[position]
        holder.itemView.setOnClickListener{
            onClickListener.onClick(place)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bindItems(data: Place){
            val name: TextView = itemView.findViewById(R.id.title)
            // distancia: TextView = itemView.findViewById(R.id.distancia)
            val image: ImageView = itemView.findViewById(R.id.imageView_1)
            name.text=data.name
            //distancia.text=data.description
            Glide.with(itemView.context).load(data.image).into(image)

        }
    }
    interface OnClickListener{
        fun onClick(place: Place)
    }
}