package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bait2073mobileapplicationdevelopment.entities.PersonalizedWorkout
import com.example.bait2073mobileapplicationdevelopment.entities.StartWorkout


@Dao
interface StartWorkoutDao {

    @Query("SELECT * FROM StartWorkout ORDER BY id DESC")
    fun getAllWorkouts() : LiveData<List<StartWorkout>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workout: StartWorkout)

    @Query("DELETE FROM personalizedworkout")
    fun clearDb()
}