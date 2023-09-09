package com.example.bait2073mobileapplicationdevelopment.screens.profile.BMI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateBmiUser
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateGenderUser
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestBmiActivityViewModel : ViewModel() {

    lateinit var updateUserLiveData: MutableLiveData<UpdateBmiUser?>



    init {
        updateUserLiveData = MutableLiveData()


    }



    fun getUpdateUserObservable(): MutableLiveData<UpdateBmiUser?> {
        return updateUserLiveData
    }

    fun updateUser(user_id: Int, user: UpdateBmiUser) {

        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.updateBmi(user_id, user)
        call.enqueue(object : Callback<UpdateBmiUser?> {
            override fun onFailure(call: Call<UpdateBmiUser?>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UpdateBmiUser?>, response: Response<UpdateBmiUser?>) {
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