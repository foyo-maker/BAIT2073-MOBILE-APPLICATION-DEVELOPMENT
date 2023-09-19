package com.example.bait2073mobileapplicationdevelopment.screens.admin.hospitalList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Hospital
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetHospitalDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalListViewModel : ViewModel() {

    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetHospitalDataService::class.java)
    private var hospitalListMut = MutableLiveData<List<Hospital>?>()
    val hospitalList: MutableLiveData<List<Hospital>?> get() = hospitalListMut

    fun getHospitalList() {
        apiService.getHospitalList().enqueue(object : Callback<List<Hospital>> {

            override fun onResponse(call: Call<List<Hospital>>, response: Response<List<Hospital>>) {
                if (response.isSuccessful) {
                    val hospitalLists = response.body()
//                        Log.e("gg", "Response not successful, code: ${hospitalLists}")
                    if (!hospitalLists.isNullOrEmpty()) {
                        // Insert the hospital data into the Room Database
                        hospitalListMut.postValue(response.body())
                    } else {
                        // Handle the case where the response is empty
                        Log.e("API Response", "Response body is empty")
                    }
                } else {
                    // Handle the case where the API response is not successful
                    Log.e("API Response", "Response not successful, code: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<Hospital>>, t: Throwable) {
                // Handle network or API errors
                Log.e("API Error", "Failed to fetch data: ${t.message}", t)
            }

        })
    }
}
