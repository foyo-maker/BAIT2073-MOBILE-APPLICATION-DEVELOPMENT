package com.example.bait2073mobileapplicationdevelopment.screens.admin.WorkoutList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserPLanListService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetWorkoutDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutListViewModel : ViewModel() {

    var recyclerListData: MutableLiveData<List<Workout?>> = MutableLiveData()
    var deleteWorkoutLiveData: MutableLiveData<Workout?> = MutableLiveData()



    fun getDeleteWorkoutObservable(): MutableLiveData<Workout?> {
        return  deleteWorkoutLiveData
    }
    fun getWorkoutListObserverable(): MutableLiveData<List<Workout?>> {
        return recyclerListData
    }


    fun deleteWorkout(workout: Workout) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetWorkoutDataService::class.java)
        val call = service.deleteWorkout(workout.id!!)
        call.enqueue(object : Callback<Workout?> {
            override fun onFailure(call: Call<Workout?>, t: Throwable) {
                Log.e("API Error", t.message ?: "Unknown error")
                deleteWorkoutLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Workout?>, response: Response<Workout?>) {
                if(response.isSuccessful) {
                    Log.e("API Response", "Response body is empty")
                    deleteWorkoutLiveData.postValue(response.body())
                } else {
                    Log.e("API Response", "Response body is empty")
                    deleteWorkoutLiveData.postValue(null)
                }
            }
        })
    }

    fun getWorkouts() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetWorkoutDataService::class.java)
        val call = service.getWorkoutList()
        call.enqueue(object : Callback<List<Workout>> {
            override fun onFailure(call: Call<List<Workout>>, t: Throwable) {
                // Handle API call failure
                Log.e("API Error", t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<Workout>>, response: Response<List<Workout>>) {
                if (response.isSuccessful) {
                    // Response contains a list of User objects
                    val userList = response.body()
                    Log.e("Response not successful", "Response not successful, code: ${userList}")
                    if (userList != null && userList.isNotEmpty()) {
                        // Insert the user data into the Room Database
                        recyclerListData.postValue(response.body())
                    } else {
                        // Handle the case where the response is empty
                        Log.e("API Response", "Response body is empty")
                    }
                } else {

                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("haha", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    // Handle the case where the API response is not successful
                    Log.e("API Response", "Response not successful, code: ${response.code()}")
                }
            }
        })
    }



}