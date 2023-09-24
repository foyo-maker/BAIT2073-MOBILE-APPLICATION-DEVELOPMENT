package com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseForm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDiseaseDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiseaseFormViewModel  : ViewModel(){
    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetDiseaseDataService::class.java)
    var createDiseaseLiveData: MutableLiveData<Disease?> = MutableLiveData()
    var loadDiseaseData: MutableLiveData<Disease?> = MutableLiveData()

    fun getCreateDiseaseObservable(): MutableLiveData<Disease?> {
        return createDiseaseLiveData
    }
    fun getLoadDiseaseObservable(): MutableLiveData<Disease?> {
        return loadDiseaseData
    }


    fun createDisease(disease: Disease) {
        apiService.createDisease(disease).enqueue(object : Callback<Disease?> {
            override fun onResponse(call: Call<Disease?>, response: Response<Disease?>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    createDiseaseLiveData.postValue(response.body())
                } else {
                    val res = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("unsuccessful", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createDiseaseLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Disease?>, t: Throwable) {
                Log.e("Error", "Unknown error")
                createDiseaseLiveData.postValue(null)
            }
        })
    }

    fun getDiseaseData(disease_id: Int?) {
        apiService.getDisease(disease_id!!).enqueue(object : Callback<Disease?> {

            override fun onResponse(call: Call<Disease?>, response: Response<Disease?>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.i("haha", "$result")
                    loadDiseaseData.postValue(response.body())
                } else {
                    Log.i("haha", "not workinggg")
                    loadDiseaseData.postValue(null)
                }
            }
            override fun onFailure(call: Call<Disease?>, t: Throwable) {
                Log.e("haha", "failed")
                loadDiseaseData.postValue(null)
            }
        })
    }
    fun updateDisease(disease_id: Int,disease: Disease) {
        apiService.updateDisease(disease_id, disease).enqueue(object : Callback<Disease?> {
            override fun onResponse(call: Call<Disease?>, response: Response<Disease?>) {
                if (response.isSuccessful) {
                    createDiseaseLiveData.postValue(response.body())
                } else {
                    val result = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("error", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createDiseaseLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<Disease?>, t: Throwable) {
                Log.e("error", "failure")
                createDiseaseLiveData.postValue(null)
            }

        })
    }

}