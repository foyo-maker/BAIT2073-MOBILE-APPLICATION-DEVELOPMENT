package com.example.bait2073mobileapplicationdevelopment.screens.dialog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateBmiUser
import com.example.bait2073mobileapplicationdevelopment.entities.UserRating
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatingDialogViewModel : ViewModel() {

    lateinit var updateUserLiveData: MutableLiveData<UserRating?>



    init {
        updateUserLiveData = MutableLiveData()


    }



    fun getUpdateUserObservable(): MutableLiveData<UserRating?> {
        return updateUserLiveData
    }

    fun updateUser(user_id: Int, user: UserRating) {


        Log.e("update","$user")
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.updateRate(user_id, user)
        call.enqueue(object : Callback<UserRating?> {
            override fun onFailure(call: Call<UserRating?>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserRating?>, response: Response<UserRating?>) {
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