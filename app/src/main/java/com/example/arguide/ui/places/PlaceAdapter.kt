package com.example.arguide.ui.places

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arguide.R
import com.example.arguide.ui.entities.Place

class PlaceAdapter(var list: List<Place>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){
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
        fun bindItems(p: Place){
            val name: TextView = itemView.findViewById(R.id.title)
            val image: ImageView = itemView.findViewById(R.id.imageView_1)
            name.text=p.name
            Glide.with(itemView.context).load(p.image).into(image)

        }
    }
    interface OnClickListener{
        fun onClick(place: Place)
    }
}