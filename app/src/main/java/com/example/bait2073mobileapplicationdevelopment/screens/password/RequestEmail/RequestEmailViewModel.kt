package com.example.bait2073mobileapplicationdevelopment.screens.password.RequestEmail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.AutenticateEmailUser
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateBmiUser
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestEmailViewModel : ViewModel() {

    lateinit var loadEmailLiveData: MutableLiveData<AutenticateEmailUser?>


    init {
        loadEmailLiveData = MutableLiveData()


    }


    fun getloadEmailObservable(): MutableLiveData<AutenticateEmailUser?> {
        return loadEmailLiveData
    }

    fun sendEmail(user: AutenticateEmailUser) {

        val service =
            RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.sendEmail(user)
        call.enqueue(object : Callback<AutenticateEmailUser?> {
            override fun onFailure(call: Call<AutenticateEmailUser?>, t: Throwable) {
                loadEmailLiveData.postValue(null)
            }

            override fun onResponse(
                call: Call<AutenticateEmailUser?>,
                response: Response<AutenticateEmailUser?>
            ) {
                if (response.isSuccessful) {

                    var responseBack = response.body()
                    Log.e("userId", "$responseBack")
                    loadEmailLiveData.postValue(response.body())
                } else {

                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e(
                        "error",
                        "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody"
                    )
                    loadEmailLiveData.postValue(null)
                }
            }
        })
    }
}