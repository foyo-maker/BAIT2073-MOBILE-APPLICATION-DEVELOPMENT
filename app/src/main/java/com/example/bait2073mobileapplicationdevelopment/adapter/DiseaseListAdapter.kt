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
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.squareup.picasso.Picasso

class DiseaseListAdapter(private val DiseaseList:MutableList<Disease>) : RecyclerView.Adapter<DiseaseListAdapter.DiseaseListViewHolder>(){

    fun setData(newData: List<Disease>) {
        DiseaseList.clear()
        DiseaseList.addAll(newData)
        notifyDataSetChanged()
    }

    inner class DiseaseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diseaseListCard = itemView.findViewById<CardView>(R.id.disease_layout)
        val diseaseId = itemView.findViewById<TextView>(R.id.tv_diseaseID)
        val diseaseName = itemView.findViewById<TextView>(R.id.tv_diseaseName)
        // Add other views as needed
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_disease, parent, false)
        return DiseaseListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiseaseListViewHolder, position: Int) {
        val currentDisease = DiseaseList[position]
        holder.diseaseId.text= currentDisease.id.toString()
        holder.diseaseName.text = currentDisease.disease_name

        val diseaseImageView = holder.itemView.findViewById<ImageView>(R.id.disease_image)
        if (!currentDisease.disease_image.isNullOrBlank()) {
            Picasso.get().load(currentDisease.disease_image).fit().into(diseaseImageView)

//            Picasso.get().load(currentCust.image).centerInside().into(custImageView)
        } else {
            // If no image URL is available,  set a placeholder image or handle this case as needed.\
            Log.e("noimage", "noimage")
            Picasso.get().load(R.drawable.image_disease).into(diseaseImageView)
        }
    }

    override fun getItemCount() = DiseaseList.size
}
