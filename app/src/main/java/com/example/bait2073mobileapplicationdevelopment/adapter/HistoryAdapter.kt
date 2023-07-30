package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.models.History
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils


class HistoryAdapter(context: Context, histories: List<History?>) :
    ArrayAdapter<History?>(context, 0, histories) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val history: History? = getItem(position)
        if (convertView == null) convertView =
            LayoutInflater.from(context).inflate(R.layout.history_cell, parent, false)
        val historyCellTV = convertView!!.findViewById<TextView>(R.id.historyCellTV)
        val historyTitle: String =
            history!!.name + " " + CalendarUtils.formattedTime(history.time)
        historyCellTV.text = historyTitle
        return convertView
    }
}
