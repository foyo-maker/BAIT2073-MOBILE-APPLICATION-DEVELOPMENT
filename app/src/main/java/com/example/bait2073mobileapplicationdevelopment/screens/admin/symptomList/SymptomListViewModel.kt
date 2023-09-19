package com.example.bait2073mobileapplicationdevelopment.screens.admin.symptomList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetSymptomDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class SymptomListViewModel: ViewModel() {

        private val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetSymptomDataService::class.java)
        var symptomListMut = MutableLiveData<List<Symptom?>>()
        var deleteSymptomLiveData: MutableLiveData<Symptom?> = MutableLiveData()

    fun getDeleteSymptomObservable(): MutableLiveData<Symptom?> {
        return deleteSymptomLiveData
    }

    fun getSymptomListObservable(): MutableLiveData<List<Symptom?>> {
        return symptomListMut
    }


    fun getSymptomList() {
            apiService.getSymptomList().enqueue(object : Callback<List<Symptom>> {

                override fun onResponse(call: Call<List<Symptom>>, response: Response<List<Symptom>>) {
                    if (response.isSuccessful) {
                        val symptomLists = response.body()
//                        Log.e("gg", "Response not successful, code: ${symptomLists}")
                        if (!symptomLists.isNullOrEmpty()) {
                            // Insert the symptom data into the Room Database
                            symptomListMut.postValue(response.body())
                        } else {
                            // Handle the case where the response is empty
                            Log.e("API Response", "Response body is empty")
                        }
                    } else {
                        // Handle the case where the API response is not successful
                        Log.e("API Response", "Response not successful, code: ${response.code()}")
                    }
            }
                override fun onFailure(call: Call<List<Symptom>>, t: Throwable) {
                    // Handle network or API errors
                    Log.e("API Error", "Failed to fetch data: ${t.message}", t)
                }

            })
    }

    fun deleteSymptom(symptom: Symptom) {

        apiService.deleteSymptom(symptom.id?:0).enqueue(object : Callback<Symptom?> {
            override fun onFailure(call: Call<Symptom?>, t: Throwable) {
                Log.e("error", "??error")
                deleteSymptomLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Symptom?>, response: Response<Symptom?>) {
                if(response.isSuccessful) {
                    Log.e("Response", "Response body empty")
                    deleteSymptomLiveData.postValue(response.body())
                } else {
                    Log.e("Response", "Response body empty")
                    deleteSymptomLiveData.postValue(null)
                }
            }
        })
    }
}
