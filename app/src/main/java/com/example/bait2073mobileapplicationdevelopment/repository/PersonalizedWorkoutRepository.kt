package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.PersonalizedWorkoutDao
import com.example.bait2073mobileapplicationdevelopment.dao.UserDao
import com.example.bait2073mobileapplicationdevelopment.entities.PersonalizedWorkout
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.Workout

class PersonalizedWorkoutRepository  (private val personalizedWorkout: PersonalizedWorkoutDao)
{

    var allWorkout: LiveData<List<PersonalizedWorkout>> = personalizedWorkout.getAllWorkouts()


    suspend fun insert(workout: PersonalizedWorkout) {
        personalizedWorkout.insertWorkout(workout)
    }
    suspend fun clearWorkout() {
        personalizedWorkout.clearDb()
    }
//    suspend fun getWorkoutById(workoutId: Int): LiveData<PersonalizedWorkout> {
//        return personalizedWorkout.getWorkoutById(workoutId)
//    }

}