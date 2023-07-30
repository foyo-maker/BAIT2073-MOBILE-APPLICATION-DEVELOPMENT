package com.example.bait2073mobileapplicationdevelopment.calendar

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.CalendarAdapter
import com.example.bait2073mobileapplicationdevelopment.adapter.HistoryAdapter
import com.example.bait2073mobileapplicationdevelopment.models.History
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils.daysInMonthArray
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils.monthYearFromDate
import java.time.LocalDate


class MonthlyViewActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {
    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
//    private var historyListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_view)
        if (CalendarUtils.selectedDate == null) {
            CalendarUtils.selectedDate = LocalDate.now()
        }
        initWidgets()
        setMonthView()

        val imgBackArrow = findViewById<ImageView>(R.id.img_back_arrow)
        imgBackArrow.setOnClickListener {
            onBackPressed()
        }


    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)
//        historyListView = findViewById(R.id.historyListView)
    }

    private fun setMonthView() {
        monthYearText!!.text = monthYearFromDate(CalendarUtils.selectedDate!!)
        val days = daysInMonthArray(CalendarUtils.selectedDate!!)
        val calendarAdapter = CalendarAdapter(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
//        setEventAdpater()
    }

    fun previousMonthAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.minusMonths(1)
        setMonthView()
    }


    override fun onResume() {
        super.onResume()
//        setEventAdpater()
    }

//    private fun setEventAdpater() {
//        val dailyEvents: ArrayList<History> = History.historyForDate(CalendarUtils.selectedDate!!)
//        val eventAdapter = HistoryAdapter(applicationContext, dailyEvents)
//        historyListView!!.adapter = eventAdapter
//    }




    override fun onItemClick(position: Int, date: LocalDate?) {
        if (date != null) {
            CalendarUtils.selectedDate = date
            setMonthView()
        }
    }
}