package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.PersonalizedWorkoutDao
import com.example.bait2073mobileapplicationdevelopment.dao.StartWorkoutDao
import com.example.bait2073mobileapplicationdevelopment.entities.PersonalizedWorkout
import com.example.bait2073mobileapplicationdevelopment.entities.StartWorkout

class StartWorkoutRepository (private val startWorkout: StartWorkoutDao)
{

    var allWorkout: LiveData<List<StartWorkout>> = startWorkout.getAllWorkouts()


    suspend fun insert(workout: StartWorkout) {
        startWorkout.insertWorkout(workout)
    }
    suspend fun clearWorkout() {
        startWorkout.clearDb()
    }
//    suspend fun getWorkoutById(workoutId: Int): LiveData<PersonalizedWorkout> {
//        return personalizedWorkout.getWorkoutById(workoutId)
//    }

}