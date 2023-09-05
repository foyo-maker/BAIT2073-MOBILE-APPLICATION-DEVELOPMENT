package com.example.bait2073mobileapplicationdevelopment.screens.staff.customer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserResponse
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateCustomerViewModel  : ViewModel() {

    lateinit var createNewUserLiveData: MutableLiveData<UserResponse?>
    lateinit var loadUserData: MutableLiveData<UserResponse?>
    lateinit var deleteUserLiveData: MutableLiveData<UserResponse?>


    init {
        createNewUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()
        deleteUserLiveData = MutableLiveData()
    }

    fun getCreateNewUserObservable(): MutableLiveData<UserResponse?> {
        return createNewUserLiveData
    }

    fun getDeleteUserObservable(): MutableLiveData<UserResponse?> {
        return deleteUserLiveData
    }

    fun getLoadUserObservable(): MutableLiveData<UserResponse?> {
        return loadUserData
    }

    fun createUser(user: User) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.createUser(user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }

    fun updateUser(user_id: Int, user: User) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.updateUser(user_id, user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }

    fun deleteUser(user_id: Int?) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    deleteUserLiveData.postValue(response.body())
                } else {
                    deleteUserLiveData.postValue(null)
                }
            }
        })
    }

    fun getUserData(user_id: Int?) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                Log.e("haha", "wandan")
                loadUserData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
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