package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.UserPlanListDao
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlan
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList

class UserPlanListRepository(private val userPlanListDao: UserPlanListDao) {

    var allUserPlanList : LiveData<List<UserPlanList>> = userPlanListDao.getAllUserPlanLists()

    // LiveData to observe all workouts by user plan ID
    fun getAllWorkoutsByUserPlanId(userPlanId: Int): LiveData<List<UserPlanList>> {
        return userPlanListDao.getAllWorkoutsByUserPlanId(userPlanId)
    }

    // Function to insert a workout into the user plan
    suspend fun insertWorkout(workout: UserPlanList) {
        userPlanListDao.insertWorkout(workout)
    }

    // Function to delete all workouts for a user plan by user plan ID
    suspend fun deleteWorkoutsByUserPlan(userPlanList: List<UserPlanList>) { // Corrected parameter type
        userPlanListDao.deleteWorkoutsByUserPlan(userPlanList)
    }
    suspend fun clearWorkout() {
        userPlanListDao.clearDb()
    }
}