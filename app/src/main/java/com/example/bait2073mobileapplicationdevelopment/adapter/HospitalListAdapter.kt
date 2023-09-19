package com.example.bait2073mobileapplicationdevelopment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.Hospital
import com.squareup.picasso.Picasso

class HospitalListAdapter (private val HospitalList:MutableList<Hospital>) : RecyclerView.Adapter<HospitalListAdapter.HospitalListViewHolder>(){

    fun setData(newData: List<Hospital>) {
        HospitalList.clear()
        HospitalList.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HospitalListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hospitalListCard = itemView.findViewById<CardView>(R.id.hospital_layout)
        val hospitalId = itemView.findViewById<TextView>(R.id.tv_hospitalID)
        val hospitalName = itemView.findViewById<TextView>(R.id.tv_hospitalName)
        // Add other views as needed
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_hospital, parent, false)
        return HospitalListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HospitalListViewHolder, position: Int) {
        val currentHospital = HospitalList[position]
        holder.hospitalId.text= currentHospital.id.toString()
        holder.hospitalName.text = currentHospital.hospital_name

        val recipeImageView = holder.itemView.findViewById<ImageView>(R.id.hospital_image)
        recipeImageView.setImageResource(R.drawable.hospital_icon)

//        if (!currentHospital.recipe_image.isNullOrBlank()) {
//            Picasso.get().load(currentRecipe.recipe_image).fit().into(recipeImageView)
//
////            Picasso.get().load(currentCust.image).centerInside().into(custImageView)
//        } else {
//            // If no image URL is available,  set a placeholder image or handle this case as needed.\
//            Log.e("noimage", "noimage")
//            Picasso.get().load(R.drawable.diseases_recipe).into(recipeImageView)
//        }
    }

    override fun getItemCount() = HospitalList.size
}