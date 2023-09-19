package com.example.bait2073mobileapplicationdevelopment.screens.admin.diseaseList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDiseaseDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class DiseaseListViewModel: ViewModel() {

    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetDiseaseDataService::class.java)
    private var diseaseListMut = MutableLiveData<List<Disease>?>()
    val diseaseList: MutableLiveData<List<Disease>?> get() = diseaseListMut

    fun getDiseaseList() {
        apiService.getDiseaseList().enqueue(object : Callback<List<Disease>> {

            override fun onResponse(call: Call<List<Disease>>, response: Response<List<Disease>>) {
                if (response.isSuccessful) {
                    val diseaseLists = response.body()
//                        Log.e("gg", "Response not successful, code: ${diseaseLists}")
                    if (!diseaseLists.isNullOrEmpty()) {
                        // Insert the disease data into the Room Database
                        diseaseListMut.postValue(response.body())
                    } else {
                        // Handle the case where the response is empty
                        Log.e("API Response", "Response body is empty")
                    }
                } else {
                    // Handle the case where the API response is not successful
                    Log.e("API Response", "Response not successful, code: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<Disease>>, t: Throwable) {
                // Handle network or API errors
                Log.e("API Error", "Failed to fetch data: ${t.message}", t)
            }

        })
    }
}
