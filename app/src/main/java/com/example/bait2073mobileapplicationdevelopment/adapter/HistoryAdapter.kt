package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.History
import com.example.bait2073mobileapplicationdevelopment.entities.StartWorkout
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.example.bait2073mobileapplicationdevelopment.utilities.CalendarUtils
import com.example.bait2073mobileapplicationdevelopment.utilities.calendar.MonthlyViewActivity
import pl.droidsonroids.gif.GifImageView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale


class HistoryAdapter(private val context: Context, private val activity: MonthlyViewActivity) :
    RecyclerView.Adapter<HistoryAdapter.WorkoutViewHolder>() {


    private var ctx: Context? = null
    var workoutList = mutableListOf<StartWorkout>()
    var fullList = mutableListOf<StartWorkout>()
    private var selectedDate: LocalDate? = null

    fun setData(arrData: List<StartWorkout>) {
        workoutList = arrData as ArrayList<StartWorkout>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        ctx = parent.context
        return WorkoutViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.history_cell, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }


    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val history_layout = itemView.findViewById<CardView>(R.id.history_layout)
        val workout_name = itemView.findViewById<TextView>(R.id.workOutTitle)
        val workoutGif = itemView.findViewById<GifImageView>(R.id.image_workout)
        val calorie = itemView.findViewById<TextView>(R.id.calorie_edit)
        val time = itemView.findViewById<TextView>(R.id.time_exercise_edit)
        val date = itemView.findViewById<TextView>(R.id.dateText)


    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {


        val currentWorkout = workoutList[position]
        holder.workout_name.text = currentWorkout.name
        // Format calorie value
        val formattedCalorie = String.format("%.1f Kcal", currentWorkout.calorie ?: 0.0)
        holder.calorie.text = formattedCalorie

        // Format duration in seconds to "mm:ss" format
        val minutes = currentWorkout.duration?.div(60) ?: 0
        val seconds = currentWorkout.duration?.rem(60) ?: 0
        val formattedDuration = String.format("%02d:%02d", minutes, seconds)
        holder.time.text = formattedDuration

        val formattedDate = formatDate(currentWorkout.currentDate.toString())
        holder.date.text = formattedDate

        Glide.with(ctx!!)
            .asGif() // Ensure that Glide knows it's a GIF
            .load(Uri.parse(currentWorkout.gifimage)) // Parse the GIF URL to a Uri
            .into(holder.workoutGif)

//        if (!currentUser.image.isNullOrBlank()) {
//            Picasso.get().load(currentUser.image).fit().into(custImageView)
//
////            Picasso.get().load(currentCust.image).centerInside().into(custImageView)
//        } else {
//            // If no image URL is available,  set a placeholder image or handle this case as needed.\
//            Log.e("noimage", "noimage")
//            Picasso.get().load(R.drawable.img_person).into(custImageView)
//        }
        //image
//        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.itemView.img_dish)


    }

    fun updateList(newList: List<StartWorkout>) {
        fullList.clear()
        fullList.addAll(newList)
        workoutList.clear()
        workoutList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filerList(search: String) {
        workoutList.clear()

        for (item in fullList) {

            if (item.name?.lowercase()
                    ?.contains(search.lowercase()) == true || item.name?.lowercase()?.contains(
                    search
                        .lowercase()
                ) == true
            ) {
                workoutList.add(item)

            }
        }

        notifyDataSetChanged()
    }


    fun setSelectedDate(date: LocalDate) {
        selectedDate = date
        // Filter and update the workoutList based on the selectedDate
        val filteredList = fullList.filter { workout ->
            workout.currentDate?.let { currentDate ->
                currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() == selectedDate
            } ?: false
        }

        Log.e("filter","$filteredList")
        workoutList.clear()
        workoutList.addAll(filteredList)
        notifyDataSetChanged()
    }

    interface WorkoutClickListener {
        fun onItemClicked(workout: StartWorkout)
    }
    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("yyyy-MM-dd") // Change the output format as needed
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }

}