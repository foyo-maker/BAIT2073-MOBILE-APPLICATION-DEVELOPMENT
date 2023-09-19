package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bait2073mobileapplicationdevelopment.entities.PersonalizedWorkout
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.Workout

@Dao
interface PersonalizedWorkoutDao {

    @Query("SELECT * FROM PersonalizedWorkout ORDER BY id DESC")
    fun getAllWorkouts() : LiveData<List<PersonalizedWorkout>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workout: PersonalizedWorkout)

    @Query("DELETE FROM personalizedworkout")
    fun clearDb()

//    @Query("SELECT * FROM workout WHERE id = :workoutId")
//    fun getWorkoutById(workoutId: Int): LiveData<PersonalizedWorkout>
}