package com.example.bait2073mobileapplicationdevelopment.screens.auth.Login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.LoginUser
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel  : ViewModel()  {

    lateinit var authenticateUserData: MutableLiveData<LoginUser?>



    init {

        authenticateUserData = MutableLiveData()

    }

    fun getAuthenticateUserObservable(): MutableLiveData<LoginUser?> {
        return authenticateUserData
    }

    fun authenticate(user: LoginUser) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.authenticate(user)
        call.enqueue(object : Callback<LoginUser?> {

            override fun onFailure(call: Call<LoginUser?>, t: Throwable) {
                Log.e("haha", "wandan")
                authenticateUserData.postValue(null)
            }

            override fun onResponse(call: Call<LoginUser?>, response: Response<LoginUser?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.i("haha", "$resposne")
                    authenticateUserData.postValue(response.body())
                } else {
                    Log.i("haha", "ggla")
                    authenticateUserData.postValue(null)
                }
            }


        })
    }
}