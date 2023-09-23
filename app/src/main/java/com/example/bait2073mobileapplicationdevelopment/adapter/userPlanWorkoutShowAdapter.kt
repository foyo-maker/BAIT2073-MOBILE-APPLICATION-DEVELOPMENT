package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.squareup.picasso.Picasso
import pl.droidsonroids.gif.GifImageView

class userPlanWorkoutShowAdapter (private val context : Context, val listener:WorkoutClickListener): RecyclerView.Adapter<userPlanWorkoutShowAdapter.WorkoutViewHolder>() {


    private var ctx: Context? = null
    var userPlanListWorkout = mutableListOf<UserPlanList>()
    var fullList = mutableListOf<UserPlanList>()


    fun setData(arrData: List<Workout>) {
        userPlanListWorkout = arrData as ArrayList<UserPlanList>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        ctx = parent.context
        return WorkoutViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_workoutlist, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userPlanListWorkout.size
    }


    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val workout_layout = itemView.findViewById<CardView>(R.id.workOutCardView)
        val workout_name = itemView.findViewById<TextView>(R.id.userPlanWorkoutName)
        val workoutGif = itemView.findViewById<GifImageView>(R.id.userPlanWorkoutGif)


    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {




        val currentWorkout = userPlanListWorkout[position]


        holder.workout_name.text = currentWorkout.name
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

        holder.workout_layout.setOnClickListener {

            listener.onItemClicked(userPlanListWorkout[holder.adapterPosition])

        }

    }

    fun updateList(newList: List<UserPlanList>) {
        fullList.clear()
        fullList.addAll(newList)
        userPlanListWorkout.clear()
        userPlanListWorkout.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filerList(search: String) {
        userPlanListWorkout.clear()

        for (item in fullList) {

            if (item.name?.lowercase()
                    ?.contains(search.lowercase()) == true || item.name?.lowercase()?.contains(
                    search
                        .lowercase()
                ) == true
            ) {
                userPlanListWorkout.add(item)

            }
        }

        notifyDataSetChanged()
    }

    interface WorkoutClickListener {
        fun onItemClicked(userPlanList: UserPlanList)

    }
}