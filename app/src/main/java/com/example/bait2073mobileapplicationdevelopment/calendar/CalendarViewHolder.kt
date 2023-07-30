package com.example.bait2073mobileapplicationdevelopment.calendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.CalendarAdapter
import java.time.LocalDate

class CalendarViewHolder constructor(
    itemView: View,
    onItemListener: CalendarAdapter.OnItemListener,
    days: ArrayList<LocalDate?> // Change the type to ArrayList<LocalDate?>
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val days: ArrayList<LocalDate?> // Change the type to ArrayList<LocalDate?>
    val parentView: View
    val dayOfMonth: TextView
    private val onItemListener: CalendarAdapter.OnItemListener

    init {
        parentView = itemView.findViewById<View>(R.id.parentView)
        dayOfMonth = itemView.findViewById<TextView>(R.id.cellDayText)
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
        this.days = days
    }

    override fun onClick(view: View) {
        onItemListener.onItemClick(adapterPosition, days[adapterPosition])
    }
}
