package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.Data.EventDataClass
import com.example.bait2073mobileapplicationdevelopment.Event.EventDetailsActivity
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.RecycleviewEventBinding
import com.example.bait2073mobileapplicationdevelopment.profile.RequestBmiActivity

class AdapterEventClass (private val dataList:ArrayList<EventDataClass>) : RecyclerView.Adapter<AdapterEventClass.ViewHolderClass>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_event,parent,false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return  dataList.size

    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvTitle.text = currentItem.dataTitle
        holder.rvDetail.text = currentItem.dataDetail
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, EventDetailsActivity::class.java)
            // Pass any necessary data to the EventDetailsActivity using Intent extras
            // For example: intent.putExtra("event_id", item.eventId)
            holder.itemView.context.startActivity(intent)
        }


    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.item_image)
        val rvTitle: TextView = itemView.findViewById(R.id.item_title)
        val rvDetail: TextView = itemView.findViewById(R.id.item_details)
    }


}