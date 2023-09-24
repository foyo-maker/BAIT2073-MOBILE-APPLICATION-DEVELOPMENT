package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.squareup.picasso.Picasso

class DiseaseMainAdapter(private val context : Context, val listener: DiseaseMainClickListener) : RecyclerView.Adapter<DiseaseMainAdapter.DiseaseMainViewHolder>() {
    var diseaseList = mutableListOf<Disease>()
    var fullList = mutableListOf<Disease>()
    private var ctx: Context? = null

    fun setData(newData: List<Disease>) {
        fullList.clear()
        fullList.addAll(newData)
        diseaseList.clear()
        diseaseList.addAll(fullList)
        notifyDataSetChanged()
    }

    inner class DiseaseMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diseaseName = itemView.findViewById<TextView>(R.id.disease_prevention_name)
        val diseaseViewBtn = itemView.findViewById<Button>(R.id.disease_prevention_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseMainViewHolder {
        ctx = parent.context
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_disease_prevention, parent, false)
        return DiseaseMainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiseaseMainViewHolder, position: Int) {
        val currentDisease = diseaseList[position]
        holder.diseaseName.text = currentDisease.disease_name

        val diseaseImageView = holder.itemView.findViewById<ImageView>(R.id.disease_prevention_image)
        if (!currentDisease.disease_image.isNullOrBlank()) {
            Glide.with(ctx!!)
                .load(currentDisease.disease_image)
                .fitCenter()
                .into(diseaseImageView)
        } else {
            Log.e("noimage", "noimage")
            Picasso.get().load(R.drawable.image_disease).into(diseaseImageView)
        }

        holder.diseaseViewBtn.setOnClickListener {
            listener.onItemClicked(diseaseList[holder.adapterPosition])
        }

    }

    fun filterList(search : String){
        diseaseList.clear()

        for(item in fullList){

            if(item.disease_name?.lowercase()?.contains(search.lowercase())==true){
                diseaseList.add(item)
            }
        }
//        Log.i("diseaselist", "$diseaseList")
        notifyDataSetChanged()
    }
    interface DiseaseMainClickListener{
        fun onItemClicked(disease: Disease)
    }
    override fun getItemCount(): Int {
        return diseaseList.size
    }

}