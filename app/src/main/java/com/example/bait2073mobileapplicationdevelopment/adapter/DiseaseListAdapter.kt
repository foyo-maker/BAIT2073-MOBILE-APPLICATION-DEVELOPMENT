package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDiseaseDataService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetSymptomDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import com.squareup.picasso.Picasso

class DiseaseListAdapter(private val context : Context, val listener: DiseaseClickListener) : RecyclerView.Adapter<DiseaseListAdapter.DiseaseListViewHolder>() {

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

    inner class DiseaseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diseaseListCard = itemView.findViewById<CardView>(R.id.disease_layout)
        val diseaseId = itemView.findViewById<TextView>(R.id.tv_diseaseID)
        val diseaseName = itemView.findViewById<TextView>(R.id.tv_diseaseName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseListViewHolder {
        ctx = parent.context
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_disease, parent, false)
        return DiseaseListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiseaseListViewHolder, position: Int) {
        val currentDisease = diseaseList[position]
        holder.diseaseId.text = currentDisease.id.toString()
        holder.diseaseName.text = currentDisease.disease_name

        val diseaseImageView = holder.itemView.findViewById<ImageView>(R.id.disease_image)
        if (!currentDisease.disease_image.isNullOrBlank()) {
            Glide.with(ctx!!)
                .load(currentDisease.disease_image)
                .fitCenter()
                .into(diseaseImageView)
        } else {

            Log.e("noimage", "noimage")
            Picasso.get().load(R.drawable.image_disease).into(diseaseImageView)
        }
        holder.diseaseListCard.setOnClickListener {

            listener.onItemClicked(diseaseList[holder.adapterPosition])

        }
        holder.diseaseListCard.setOnLongClickListener {

            listener.onLongItemClicked(diseaseList[holder.adapterPosition], holder.diseaseListCard)
            true
        }
    }

    fun filterList(search : String){
        diseaseList.clear()

        for(item in fullList){

            if(item.disease_name?.lowercase()?.contains(search.lowercase())==true){
                diseaseList.add(item)
            }
        }
        Log.i("symptomlist", "$diseaseList")
        notifyDataSetChanged()
    }
    interface DiseaseClickListener{
        fun onItemClicked(disease: Disease)
        fun onLongItemClicked(disease: Disease,cardView: CardView)
    }
    override fun getItemCount(): Int {
        return diseaseList.size
    }

}



