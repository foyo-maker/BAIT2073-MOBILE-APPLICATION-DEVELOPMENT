package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentEventListBinding
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.ListAdapter
import kotlinx.coroutines.CoroutineScope


class EventListAdapter (private val context: Context, val listener:EventClickListerner):RecyclerView.Adapter<EventListAdapter.EventViewHolder>(){


    private var ctx:Context?=null
    var eventList = mutableListOf<Event>()
    var fullList = mutableListOf<Event>()


    fun setData(arrData: List<Event>) {
        eventList = arrData as ArrayList<Event>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        ctx = parent.context
        return EventViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_event_list_admin,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class EventViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val event_layout= itemView.findViewById<CardView>(R.id.event_layout)
        val id= itemView.findViewById<TextView>(R.id.tv_eventID)
        val eventTitle = itemView.findViewById<TextView>(R.id.tv_eventTitle)
        val eventDate = itemView.findViewById<TextView>(R.id.tv_eventDate)

    }


    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = eventList[position]

        holder.id.text = currentEvent.id.toString()?: ""
        holder.eventTitle.text = currentEvent.title?: ""
        holder.eventDate.text = currentEvent.date?: ""
        Log.e("EventListAdapter","${currentEvent.image} event image")

        val eventImageView =  holder.itemView.findViewById<ImageView>(R.id.event_image)

        if (!currentEvent.image.isNullOrBlank()) {
            Picasso.get().load(currentEvent.image).fit().into(eventImageView)
        }else{
            Log.e("EventListAdapter","event image")
            Picasso.get().load(R.drawable.event_default)
        }

        holder.event_layout.setOnClickListener{
            listener.onItemClicked(eventList[holder.adapterPosition])
        }
        holder.event_layout.setOnLongClickListener{
            listener.onLongItemClicked(eventList[holder.adapterPosition],holder.event_layout)
            true
        }
    }


    fun updateList(newList:List<Event>){
        fullList.clear()
        fullList.addAll(newList)
        eventList.clear()
        eventList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search : String){
        eventList.clear()

        for(item in fullList){
            if (item.title?.lowercase()?.contains(search.lowercase())==true||item.date?.lowercase()?.contains(search.lowercase())==true){
                eventList.add(item)
            }
        }
        Log.i("eventList","$eventList")
        notifyDataSetChanged()

    }


    interface EventClickListerner{
        fun onItemClicked(event: Event)
        fun onLongItemClicked(event:Event,cardView:CardView)
    }


}