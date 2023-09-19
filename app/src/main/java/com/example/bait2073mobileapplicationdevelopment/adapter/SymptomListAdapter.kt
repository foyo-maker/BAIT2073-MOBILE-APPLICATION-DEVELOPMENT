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
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom
import com.squareup.picasso.Picasso

class SymptomListAdapter(private val context : Context, val listener: SymptomClickListener) : RecyclerView.Adapter<SymptomListAdapter.SymptomListViewHolder>(){
    var symptomList = mutableListOf<Symptom>()
    var fullList = mutableListOf<Symptom>()
    private var ctx: Context? = null

    fun setData(newData: List<Symptom>) {
        fullList.clear()
        fullList.addAll(newData)
        symptomList.clear()
        symptomList.addAll(fullList)
        notifyDataSetChanged()
    }

    inner class SymptomListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val symptomListCard = itemView.findViewById<CardView>(R.id.symptom_layout)
        val symptomId = itemView.findViewById<TextView>(R.id.tv_symptomID)
        val symptomName = itemView.findViewById<TextView>(R.id.tv_symptomName)
        // Add other views as needed
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomListViewHolder {
            ctx=parent.context
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_symptom, parent, false)
            return SymptomListViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: SymptomListViewHolder, position: Int) {
            val currentSymptom = symptomList[position]
            holder.symptomId.text= currentSymptom.id.toString()
            holder.symptomName.text = currentSymptom.symptom_name

            val symptomImageView = holder.itemView.findViewById<ImageView>(R.id.symptom_image)
            if (!currentSymptom.symptom_image.isNullOrBlank()) {
                Glide.with(ctx!!)
                    .load(currentSymptom.symptom_image)
                    .fitCenter()
                    .into(symptomImageView)
            } else {
                // If no image URL is available,  set a placeholder image or handle this case as needed.\
                Log.e("noimage", "noimage")
                Picasso.get().load(R.drawable.image_symptom).into(symptomImageView)
            }
            holder.symptomListCard.setOnClickListener{

                listener.onItemClicked(symptomList[holder.adapterPosition])

            }
            holder.symptomListCard.setOnLongClickListener{

                listener.onLongItemClicked(symptomList[holder.adapterPosition],holder.symptomListCard)
                true
            }
        }

        override fun getItemCount():Int{
            return symptomList.size
        }

    fun filterList(search : String){
        symptomList.clear()

        for(item in fullList){

            if(item.symptom_name?.lowercase()?.contains(search.lowercase())==true){
                symptomList.add(item)
            }
        }
        Log.i("symptomlist", "$symptomList")
        notifyDataSetChanged()
    }

    interface SymptomClickListener{
        fun onItemClicked(symptom: Symptom)
        fun onLongItemClicked(symptom: Symptom, cardView:CardView)
    }

}
