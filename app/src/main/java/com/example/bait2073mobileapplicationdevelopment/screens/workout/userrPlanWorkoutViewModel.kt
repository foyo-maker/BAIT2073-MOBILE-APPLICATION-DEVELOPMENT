package com.example.bait2073mobileapplicationdevelopment.screens.workout

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetUserPLanListService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class userrPlanWorkoutViewModel  : ViewModel() {

    var recyclerListData: MutableLiveData<List<UserPlanList?>> = MutableLiveData()
    var deleteUserPlanList: MutableLiveData<UserPlanList?> = MutableLiveData()


    fun getWorkoutListObserverable(): MutableLiveData<List<UserPlanList?>> {
        return recyclerListData
    }


    fun getUserPlanWorkoutList(userPlanID: Int?) {


        val service =
            RetrofitClientInstance.retrofitInstance!!.create(GetUserPLanListService::class.java)
        val call = service.getUserPlanListByUserPlanId(userPlanID!!)
        call.enqueue(object : Callback<List<UserPlanList>> {
            override fun onFailure(call: Call<List<UserPlanList>>, t: Throwable) {
                // Handle API call failure
                Log.e("API Error", t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<UserPlanList>>, response: Response<List<UserPlanList>>) {
                if (response.isSuccessful) {
                    // Response contains a list of User objects
                    val userPlanWorkoutList = response.body()
                    Log.e("gg", "Response not successful, code: ${userPlanWorkoutList}")
                    if (userPlanWorkoutList != null && userPlanWorkoutList.isNotEmpty()) {
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
//    fun deleteUserPlanList(userPlanListId: Int?) {
//        Log.e("DeletePlanList","$userPlanListId")
//        if (userPlanListId != null) {
//            // Directly delete the user plan
//            val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserPLanListService::class.java)
//            val call = service.deleteUserPlanListById(userPlanListId)
//            call.enqueue(object : Callback<UserPlanList?> {
//
//                override fun onFailure(call: Call<UserPlanList?>, t: Throwable) {
//                    Log.e("API Error", t.message ?: "Unknown error")
//                    deleteUserPlanList.postValue(null)
//                }
//
//                override fun onResponse(call: Call<UserPlanList?>, response: Response<UserPlanList?>) {
//                    if (response.isSuccessful) {
//                        Log.e("API Response", "Response body is success")
//                        deleteUserPlanList.postValue(response.body())
//
//                    } else {
//                        Log.e("API Response", "Response body is fail")
//                        deleteUserPlanList.postValue(null)
//                        val resposne = response.body()
//                        val errorBody = response.errorBody()?.string()
//                        val responseCode = response.code()
//                        val responseMessage = response.message()
//                        Log.e("haha", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
//                    }
//                }
//            })
//        }
//    }
fun deleteUserPlanList(id: Int?) {
    val service = RetrofitClientInstance.retrofitInstance!!.create(GetUserPLanListService::class.java)
    val call = service.deleteUserPlanListById(id)
    call.enqueue(object : Callback<UserPlanList> {

        override fun onFailure(call: Call<UserPlanList>, t: Throwable) {
            Log.e("API Error", t.message ?: "Unknown error")
            // Handle the error as needed, e.g., show an error message
        }

        override fun onResponse(call: Call<UserPlanList>, response: Response<UserPlanList>) {
            if (response.isSuccessful) {
                Log.e("API Response", "Response body is success")
                // Handle the successful deletion here, if needed
            } else {
                Log.e("API Response", "Response body is fail")
                Log.e("API Response", "Response body is fail")
                        deleteUserPlanList.postValue(null)
                        val resposne = response.body()
                        val errorBody = response.errorBody()?.string()
                        val responseCode = response.code()
                        val responseMessage = response.message()
                        Log.e("haha", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
            }
        }
    })
}



}
