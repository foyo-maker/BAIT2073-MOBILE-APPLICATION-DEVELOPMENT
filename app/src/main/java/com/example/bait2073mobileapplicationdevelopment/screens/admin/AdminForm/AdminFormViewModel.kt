package com.example.bait2073mobileapplicationdevelopment.screens.admin.AdminForm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminFormViewModel  : ViewModel() {

    lateinit var createNewUserLiveData: MutableLiveData<User?>
    lateinit var loadUserData: MutableLiveData<User?>



    init {
        createNewUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()

    }

    fun getCreateNewUserObservable(): MutableLiveData<User?> {
        return createNewUserLiveData
    }


    fun getLoadUserObservable(): MutableLiveData<User?> {
        return loadUserData
    }

    fun createUser(user: User) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.createUser(user)
        call.enqueue(object : Callback<User?> {
            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.e("haha", "wandan")
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.i("haha", "$resposne")
                    createNewUserLiveData.postValue(response.body())
                } else {
                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("haha", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }



    fun updateUser(user_id: Int, user: User) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.updateUser(user_id, user)
        call.enqueue(object : Callback<User?> {
            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.e("error", "failure")
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {

                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("error", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }


    fun getUserData(user_id: Int?) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.getUser(user_id!!)
        call.enqueue(object : Callback<User?> {

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.e("haha", "wandan")
                loadUserData.postValue(null)
            }

            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.i("haha", "$resposne")
                    loadUserData.postValue(response.body())
                } else {
                    Log.i("haha", "ggla")
                    loadUserData.postValue(null)
                }
            }


        })
    }

}