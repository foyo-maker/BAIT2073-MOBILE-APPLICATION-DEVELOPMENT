package com.example.bait2073mobileapplicationdevelopment.utilities.calendar

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.CalendarAdapter
import com.example.bait2073mobileapplicationdevelopment.adapter.HistoryAdapter
import com.example.bait2073mobileapplicationdevelopment.adapter.PersonalizedWorkOutAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityHistoryViewBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDisplayPersonalizedWorkoutBinding
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils.daysInMonthArray
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils.monthYearFromDate
import com.example.bait2073mobileapplicationdevelopment.viewmodel.StartWorkOutViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date


class MonthlyViewActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {
    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private lateinit var binding: ActivityHistoryViewBinding
    lateinit var viewModelStartWorkout: StartWorkOutViewModel
    lateinit var recyclerViewAdapter: HistoryAdapter

    //    private var historyListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHistoryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (CalendarUtils.selectedDate == null) {
            CalendarUtils.selectedDate = LocalDate.now()
        }

        initRecyclerView()
        initWidgets()
        setMonthView()

        viewModelStartWorkout = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(StartWorkOutViewModel::class.java)

        binding.currentDateTV.text = LocalDate.now().toString()





        viewModelStartWorkout.allStartWorkout.observe(this) { list ->
            list?.let {
                recyclerViewAdapter.updateList(it)
                recyclerViewAdapter.notifyDataSetChanged()


                val todayDate = LocalDate.now()

                val filteredList = it.filter { workout ->
                    workout.currentDate?.let { currentDate ->
                        val workoutDate =
                            currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        workoutDate == todayDate
                    } ?: false
                }

// Calculate the workout count for today
                val workoutCountForToday = "${filteredList.size} workouts"

// Calculate the overall workout calorie total for today
                val overallCalorieTotalForToday = filteredList.sumByDouble { it.calorie ?: 0.0 }

// Calculate the overall workout time spent for today
                val overallTimeSpentForTodayInSeconds = filteredList.sumBy { it.duration ?: 0 }

// Format the overall calorie total and time spent
                val formattedCalorieTotal = String.format("%.1f Kcal", overallCalorieTotalForToday)
                val formattedTimeSpent = String.format(
                    "%02d:%02d Time",
                    overallTimeSpentForTodayInSeconds / 60,
                    overallTimeSpentForTodayInSeconds % 60
                )

                binding.workoutcountEdit.text = workoutCountForToday.toString()
                binding.kcalEdit.text = formattedCalorieTotal
                binding.timeEdit.text = formattedTimeSpent
            }
        }

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


    override fun onItemClick(position: Int, date: LocalDate?) {
        if (date != null) {
            CalendarUtils.selectedDate = date

            // Format the selected date in the desired format "dd/MM/yyyy"
            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formattedDate = date.format(dateFormatter)

            // Calculate the workout count for the selected date
            val workoutCountForSelectedDate = viewModelStartWorkout.allStartWorkout.value?.count {
                it.currentDate?.let { currentDate ->
                    convertDateToLocalDate(currentDate) == date
                } ?: false
            } ?: 0

            // Calculate the overall workout calorie total for the selected date
            val overallCalorieTotalForSelectedDate =
                viewModelStartWorkout.allStartWorkout.value?.filter {
                    it.currentDate?.let { currentDate ->
                        convertDateToLocalDate(currentDate) == date
                    } ?: false
                }?.sumByDouble { it.calorie ?: 0.0 } ?: 0.0

            // Calculate the overall workout time spent for the selected date
            val overallTimeSpentForSelectedDateInSeconds =
                viewModelStartWorkout.allStartWorkout.value?.filter {
                    it.currentDate?.let { currentDate ->
                        convertDateToLocalDate(currentDate) == date
                    } ?: false
                }?.sumBy { it.duration ?: 0 } ?: 0

            // Format the overall calorie total and time spent for the selected date
            val formattedCalorieTotalForSelectedDate =
                String.format("%.1f Kcal", overallCalorieTotalForSelectedDate)
            val formattedTimeSpentForSelectedDate = String.format(
                "%02d:%02d Time",
                overallTimeSpentForSelectedDateInSeconds / 60,
                overallTimeSpentForSelectedDateInSeconds % 60
            )

            // Update the TextViews with the selected date and calculated values
            binding.currentDateTV.text = formattedDate

            binding.workoutcountEdit.text = "${workoutCountForSelectedDate.toString()} workouts"
            binding.kcalEdit.text = formattedCalorieTotalForSelectedDate
            binding.timeEdit.text = formattedTimeSpentForSelectedDate

            // Update the RecyclerView with workouts for the selected date
            recyclerViewAdapter.setSelectedDate(date)
            setMonthView()
        }
    }

    private fun convertDateToLocalDate(date: Date): LocalDate {
        return Instant.ofEpochMilli(date.time)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    private fun initRecyclerView() {
        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        recyclerViewAdapter = HistoryAdapter(this, this)
        binding.recycleView.adapter = recyclerViewAdapter

    }
}