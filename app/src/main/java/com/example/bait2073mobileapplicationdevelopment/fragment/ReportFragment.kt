package com.example.bait2073mobileapplicationdevelopment.fragment

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.CalendarAdapter
import com.example.bait2073mobileapplicationdevelopment.calendar.MonthlyViewActivity
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils
import java.time.LocalDate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportFragment : Fragment(), CalendarAdapter.OnItemListener {

    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_report, container, false)
        if (CalendarUtils.selectedDate == null) {
            CalendarUtils.selectedDate = LocalDate.now()
        }
        initWidgets(view)
        setWeekView()


        val nextHistoryImageView = view.findViewById<ImageView>(R.id.nextHistory)
        nextHistoryImageView.setOnClickListener {
            // Create an intent to navigate to the destination activity
            val intent = Intent(requireContext(), MonthlyViewActivity::class.java)
            // Start the destination activity
            startActivity(intent)
        }
        val heightTextView = view.findViewById<TextView>(R.id.height_edit)
        // Set the text you want to display
        heightTextView.text = "178.0"
         // Underline the text programmatically

        return view

    }

    private fun initWidgets(view: View) {
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView)
        monthYearText = view.findViewById(R.id.monthYearTV)

    }

    private fun setWeekView() {
        monthYearText!!.text = CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate!!)
        val days = CalendarUtils.daysInWeekArray(CalendarUtils.selectedDate!!)
        val calendarAdapter = CalendarAdapter(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 7)
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
//        setEventAdpater()
    }

    fun previousWeekAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.minusWeeks(1)
        setWeekView()
    }

    fun nextWeekAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.plusWeeks(1)
        setWeekView()
    }


    override fun onResume() {
        super.onResume()
//        setEventAdpater()
    }

//    private fun setEventAdpater() {
//        val dailyEvents: ArrayList<Event> = Event.eventsForDate(CalendarUtils.selectedDate)
//        val eventAdapter = EventAdapter(applicationContext, dailyEvents)
//        eventListView!!.adapter = eventAdapter
//    }
//
//    fun newEventAction(view: View?) {
//        startActivity(Intent(this, EventEditActivity::class.java))
//    }


    override fun onItemClick(position: Int, date: LocalDate?) {
        CalendarUtils.selectedDate = date
        setWeekView()
    }


}