package com.example.bait2073mobileapplicationdevelopment.screens.workout

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetPersonalizedWorkoutDataService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserPLanListService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class userrPlanWorkoutViewModel  : ViewModel() {

    var recyclerListData: MutableLiveData<List<UserPlanList?>> = MutableLiveData()



    fun getWorkoutListObserverable(): MutableLiveData<List<UserPlanList?>> {
        return recyclerListData
    }


    fun getPersonalizedWorkoutList(userPlanID: Int?) {


        val service =
            RetrofitClientInstance.retrofitInstance!!.create(GetUserPLanListService::class.java)
        val call = service.getUserPlanListByUserPlanId(userPlanID!!)
        call.enqueue(object : Callback<List<UserPlanList>> {
            override fun onFailure(call: Call<List<UserPlanList>>, t: Throwable) {
                // Handle API call failure
                Log.e("API Error", t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<UserPlanList>>, response: Response<List<UserPlanList>>) {
                if (response.isSuccessful) {
                    // Response contains a list of User objects
                    val userPlanWorkoutList = response.body()
                    Log.e("gg", "Response not successful, code: ${userPlanWorkoutList}")
                    if (userPlanWorkoutList != null && userPlanWorkoutList.isNotEmpty()) {
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
