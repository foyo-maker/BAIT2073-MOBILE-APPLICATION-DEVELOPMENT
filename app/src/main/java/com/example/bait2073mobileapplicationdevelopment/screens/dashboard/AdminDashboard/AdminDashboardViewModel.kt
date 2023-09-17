package com.example.bait2073mobileapplicationdevelopment.screens.dashboard.AdminDashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminDashboardViewModel  : ViewModel() {


    lateinit var loadUserData: MutableLiveData<User?>
    var userListData: MutableLiveData<List<User?>> = MutableLiveData()

    var adminListData: MutableLiveData<List<User?>> = MutableLiveData()
    init {
        loadUserData = MutableLiveData()
    }



    fun getUserListObserverable(): MutableLiveData<List<User?>> {
        return userListData
    }

    fun getAdminListObserverable(): MutableLiveData<List<User?>> {
        return adminListData
    }

    fun getLoadUserObservable(): MutableLiveData<User?> {
        return loadUserData
    }




    fun getAdmins() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.getAdminList()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Handle API call failure
                Log.e("API Error", t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    // Response contains a list of User objects
                    val userList = response.body()

                    if (userList != null && userList.isNotEmpty()) {
                        // Insert the user data into the Room Database
                        adminListData.postValue(response.body())
                    } else {
                        // Handle the case where the response is empty
                        Log.e("API Response", "Response body is empty")
                    }
                } else {
                    // Handle the case where the API response is not successful
                    Log.e("API Response", "Response not successful, code: ${response.code()}")
                }
            }
        })
    }


    fun getUsers() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserDataService::class.java)
        val call = service.getUserList()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Handle API call failure
                Log.e("API Error", t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    // Response contains a list of User objects
                    val userList = response.body()
                    Log.e("gg", "Response not successful, code: ${userList}")
                    if (userList != null && userList.isNotEmpty()) {
                        // Insert the user data into the Room Database
                        userListData.postValue(response.body())
                    } else {
                        // Handle the case where the response is empty
                        Log.e("API Response", "Response body is empty")
                    }
                } else {
                    // Handle the case where the API response is not successful
                    Log.e("API Response", "Response not successful, code: ${response.code()}")
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