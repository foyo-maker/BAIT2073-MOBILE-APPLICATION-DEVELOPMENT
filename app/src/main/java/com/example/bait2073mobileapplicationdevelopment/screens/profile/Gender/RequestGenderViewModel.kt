package com.example.bait2073mobileapplicationdevelopment.screens.profile.Gender

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
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.updateGender(user_id, user)
        call.enqueue(object : Callback<UpdateGenderUser?> {
            override fun onFailure(call: Call<UpdateGenderUser?>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UpdateGenderUser?>, response: Response<UpdateGenderUser?>) {
                if (response.isSuccessful) {
                    updateUserLiveData.postValue(response.body())
                } else {
                    updateUserLiveData.postValue(null)
                }
            }
        })
    }
}