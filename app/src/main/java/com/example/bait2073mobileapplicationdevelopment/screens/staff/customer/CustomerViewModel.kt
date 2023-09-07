package com.example.bait2073mobileapplicationdevelopment.screens.staff.customer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserList
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerViewModel : ViewModel() {

    var recyclerListData: MutableLiveData<List<User?>> = MutableLiveData()
     var deleteUserLiveData: MutableLiveData<User?> = MutableLiveData()



    fun getDeleteUserObservable(): MutableLiveData<User?> {
        return  deleteUserLiveData
    }
    fun getUserListObserverable(): MutableLiveData<List<User?>> {
        return recyclerListData
    }


    fun getUsers() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
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
                        recyclerListData.postValue(response.body())
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

    fun deleteUser(user: User) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.deleteUser(user.id?:0)
        call.enqueue(object : Callback<User?> {

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.e("haha", "dfd")
                deleteUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if(response.isSuccessful) {
                    Log.e("haha", "wandan")
                    deleteUserLiveData.postValue(response.body())
                } else {
                    Log.e("haha", "wandan")
                    deleteUserLiveData.postValue(null)
                }
            }
        })
    }

//    fun getUsersList() {
//        val retroInstance = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
//
//        val call = retroInstance.getAllUsers()
//        call.enqueue(object : Callback<UserList> {
//            override fun onFailure(call: Call<UserList>, t: Throwable) {
//                recyclerListData.postValue(null)
//                Log.i("haha", "gg,noob")
//            }
//
//            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
//                if (response.isSuccessful) {
//                    val userList = response.body()
//                    Log.i("haha", "gg, code: ${userList}")
//                    recyclerListData.postValue(response.body())
//                } else {
//                    recyclerListData.postValue(null)
//                }
//            }
//        })
//    }

//    fun searchUser(searchText: String) {
//
//        val retroInstance = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
//        val call = retroInstance.searchUsers(searchText)
//        call.enqueue(object : Callback<UserList> {
//            override fun onFailure(call: Call<UserList>, t: Throwable) {
//                recyclerListData.postValue(null)
//            }
//
//            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
//                if (response.isSuccessful) {
//                    recyclerListData.postValue(response.body())
//                } else {
//                    recyclerListData.postValue(null)
//                }
//            }
//        })
//    }
}
