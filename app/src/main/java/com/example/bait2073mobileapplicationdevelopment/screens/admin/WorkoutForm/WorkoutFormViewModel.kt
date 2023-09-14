package com.example.bait2073mobileapplicationdevelopment.screens.admin.WorkoutForm

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

class WorkoutFormViewModel : ViewModel() {

    lateinit var createNewUserLiveData: MutableLiveData<Workout?>

    init {
        createNewUserLiveData = MutableLiveData()


    }

    fun getCreateNewWorkOutObservable(): MutableLiveData<Workout?> {
        return createNewUserLiveData
    }



    fun createWorkout(workout: Workout) {
        val service =
            RetrofitClientInstance.retrofitInstance!!.create(GetPersonalizedWorkoutDataService::class.java)
        val call = service.createWorkout(workout)
        call.enqueue(object : Callback<Workout?> {
            override fun onFailure(call: Call<Workout?>, t: Throwable) {
                Log.e("haha", "wandan")
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Workout?>, response: Response<Workout?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.i("haha", "$resposne")
                    createNewUserLiveData.postValue(response.body())
                } else {
                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e(
                        "haha",
                        "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody"
                    )
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }


}