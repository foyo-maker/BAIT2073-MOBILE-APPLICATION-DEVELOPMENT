package com.example.bait2073mobileapplicationdevelopment.screens.password.ChangePassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.UpdatePasswordUser
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel : ViewModel() {

    lateinit var updatePasswordLiveData: MutableLiveData<UpdatePasswordUser?>




    init {
        updatePasswordLiveData = MutableLiveData()


    }

    fun getUpdatePasswordObservable(): MutableLiveData<UpdatePasswordUser?> {
        return updatePasswordLiveData
    }

    fun updatePassword(user_id: Int, user: UpdatePasswordUser) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.updatePassword(user_id, user)
        call.enqueue(object : Callback<UpdatePasswordUser?> {
            override fun onFailure(call: Call<UpdatePasswordUser?>, t: Throwable) {
                Log.e("error", "failure")
                updatePasswordLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UpdatePasswordUser?>, response: Response<UpdatePasswordUser?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.e("success", "$resposne")
                    updatePasswordLiveData.postValue(response.body())
                } else {

                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("error", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    updatePasswordLiveData.postValue(null)
                }
            }
        })
    }




}