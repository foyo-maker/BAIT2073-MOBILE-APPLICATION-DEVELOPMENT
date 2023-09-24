package com.example.bait2073mobileapplicationdevelopment.screens.workout

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserDataService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserPLanListService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetWorkoutDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPlanListViewModel : ViewModel() {

    var recyclerListData: MutableLiveData<List<Workout?>> = MutableLiveData()



    lateinit var createNewUserPlanLiveData: MutableLiveData<UserPlanList?>

    init {
        createNewUserPlanLiveData = MutableLiveData()


    }

    fun getWorkoutObserverable(): MutableLiveData<List<Workout?>> {
        return recyclerListData
    }


    fun getCreateNewUserPlanObservable(): MutableLiveData<UserPlanList?> {
        return createNewUserPlanLiveData
    }


    fun getWorkouts() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetWorkoutDataService::class.java)
        val call = service.getWorkoutList()
        call.enqueue(object : Callback<List<Workout>> {
            override fun onFailure(call: Call<List<Workout>>, t: Throwable) {
                // Handle API call failure
                Log.e("API Error", t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<Workout>>, response: Response<List<Workout>>) {
                if (response.isSuccessful) {
                    // Response contains a list of User objects
                    val userList = response.body()
                    Log.e("Response not successful", "Response not successful, code: ${userList}")
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


    fun createPlanWorkout(userPlanList: UserPlanList) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserPLanListService::class.java)
        val call = service.insertUserPlanList(userPlanList)
        call.enqueue(object : Callback<UserPlanList?> {
            override fun onFailure(call: Call<UserPlanList?>, t: Throwable) {
                Log.e("API Error", t.message ?: "Unknown error")
                createNewUserPlanLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserPlanList?>, response: Response<UserPlanList?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    createNewUserPlanLiveData.postValue(response.body())
                } else {
                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("haha", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createNewUserPlanLiveData.postValue(null)
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