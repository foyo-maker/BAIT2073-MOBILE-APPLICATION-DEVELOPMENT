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
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.Hospital

class HospitalListAdapter (private val context : Context, val listener: HospitalListAdapter.HospitalClickListener) : RecyclerView.Adapter<HospitalListAdapter.HospitalListViewHolder>(){
    var hospitalList = mutableListOf<Hospital>()
    var fullList = mutableListOf<Hospital>()
    private var ctx: Context? = null

    fun setData(newData: List<Hospital>) {
        fullList.clear()
        fullList.addAll(newData)
        hospitalList.clear()
        hospitalList.addAll(fullList)
        notifyDataSetChanged()
    }

    inner class HospitalListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hospitalListCard = itemView.findViewById<CardView>(R.id.hospital_layout)
        val hospitalId = itemView.findViewById<TextView>(R.id.tv_hospitalID)
        val hospitalName = itemView.findViewById<TextView>(R.id.tv_hospitalName)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalListViewHolder {
        ctx=parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_hospital, parent, false)
        return HospitalListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HospitalListViewHolder, position: Int) {
        val currentHospital = hospitalList[position]
        holder.hospitalId.text= currentHospital.id.toString()
        holder.hospitalName.text = currentHospital.hospital_name

        val hospitalImageView = holder.itemView.findViewById<ImageView>(R.id.hospital_image)
       hospitalImageView.setImageResource(R.drawable.hospital_icon)

        holder.hospitalListCard.setOnClickListener{

            listener.onItemClicked(hospitalList[holder.adapterPosition])

        }
        holder.hospitalListCard.setOnLongClickListener{

            listener.onLongItemClicked(hospitalList[holder.adapterPosition],holder.hospitalListCard)
            true
        }
    }
    override fun getItemCount():Int{
        return hospitalList.size
    }

    fun filterList(search : String){
        hospitalList.clear()

        for(item in fullList){

            if(item.hospital_name?.lowercase()?.contains(search.lowercase())==true){
               hospitalList.add(item)
            }
        }
        Log.i("hospitallist", "$hospitalList")
        notifyDataSetChanged()
    }

    interface HospitalClickListener{
        fun onItemClicked(hospital: Hospital)
        fun onLongItemClicked(hospital: Hospital, cardView:CardView)
    }

}