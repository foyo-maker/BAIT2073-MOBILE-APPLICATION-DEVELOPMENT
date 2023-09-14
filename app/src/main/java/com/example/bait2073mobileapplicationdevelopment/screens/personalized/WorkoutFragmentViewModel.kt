package com.example.bait2073mobileapplicationdevelopment.screens.personalized

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetPersonalizedWorkoutDataService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutFragmentViewModel  : ViewModel() {

    var recyclerListData: MutableLiveData<List<Workout?>> = MutableLiveData()



    fun getWorkoutListObserverable(): MutableLiveData<List<Workout?>> {
        return recyclerListData
    }


    fun getPersonalizedWorkoutList(user_id: Int?) {


        val service =
            RetrofitClientInstance.retrofitInstance!!.create(GetPersonalizedWorkoutDataService::class.java)
        val call = service.getPersonalizedWorkoutList(user_id!!)
        call.enqueue(object : Callback<List<Workout>> {
            override fun onFailure(call: Call<List<Workout>>, t: Throwable) {
                // Handle API call failure
                Log.e("API Error", t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<Workout>>, response: Response<List<Workout>>) {
                if (response.isSuccessful) {
                    // Response contains a list of User objects
                    val workoutList = response.body()
                    Log.e("gg", "Response not successful, code: ${workoutList}")
                    if (workoutList != null && workoutList.isNotEmpty()) {
                        // Insert the user data into the Room Database
                        recyclerListData.postValue(response.body())
                    } else {
                        // Handle the case where the response is empty
                        Log.e("API Response", "Response body is empty")
                    }
                } else {
                    // Handle the case where the API response is not successful
                    Log.e("API Response", "Response not successful, code: ${response.code()}")
                }
            }
        })
    }



}
