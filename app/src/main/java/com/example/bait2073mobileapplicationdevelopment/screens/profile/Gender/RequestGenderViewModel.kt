package com.example.bait2073mobileapplicationdevelopment.screens.profile.Gender

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.RegisterUser
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateGenderUser
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestGenderViewModel  : ViewModel() {

    lateinit var updateUserLiveData: MutableLiveData<UpdateGenderUser?>



    init {
        updateUserLiveData = MutableLiveData()


    }



    fun getUpdateUserObservable(): MutableLiveData<UpdateGenderUser?> {
        return updateUserLiveData
    }

    fun updateUser(user_id: Int, user: UpdateGenderUser) {
        Log.e("upateener", "upategender")
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.updateGender(user_id, user)
        call.enqueue(object : Callback<UpdateGenderUser?> {
            override fun onFailure(call: Call<UpdateGenderUser?>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UpdateGenderUser?>, response: Response<UpdateGenderUser?>) {
                if (response.isSuccessful) {

                    var responseBack = response.body()
                    Log.e("userId", "$responseBack")
                    updateUserLiveData.postValue(response.body())
                } else {

                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("error", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    updateUserLiveData.postValue(null)
                }
            }
        })
    }
}