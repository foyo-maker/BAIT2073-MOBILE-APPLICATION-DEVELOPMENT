package com.example.bait2073mobileapplicationdevelopment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.Data.EventDataClass
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.fragment.EventDetailsFragment
import com.example.bait2073mobileapplicationdevelopment.fragment.WorkoutFragment

class AdapterEventClass(
    private val dataList: ArrayList<EventDataClass>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterEventClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_event, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvTitle.text = currentItem.dataTitle
        holder.rvDetail.text = currentItem.dataDetail
        holder.itemView.setOnClickListener { view: View ->
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_layout, EventDetailsFragment())
            fragmentTransaction.addToBackStack(null) // Add to back stack if you want to navigate back
            fragmentTransaction.commit()
        }
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.item_image)
        val rvTitle: TextView = itemView.findViewById(R.id.item_title)
        val rvDetail: TextView = itemView.findViewById(R.id.item_details)
    }
}
