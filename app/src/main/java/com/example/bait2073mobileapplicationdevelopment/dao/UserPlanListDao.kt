package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import androidx.room.Query

@Dao
interface UserPlanListDao {

    @Query("SELECT * FROM User_Plan_List")
    fun getAllUserPlanLists(): LiveData<List<UserPlanList>>
    @Query("SELECT * FROM User_Plan_List WHERE user_plan_id = :user_plan_id")
    fun getAllWorkoutsByUserPlanId(user_plan_id: Int): LiveData<List<UserPlanList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workout: UserPlanList)

    @Delete
    fun deleteWorkoutsByUserPlan(userPlanList: List<UserPlanList>)  // Corrected the parameter type

    @Query("DELETE FROM User_Plan_List")
    fun clearDb()
}
