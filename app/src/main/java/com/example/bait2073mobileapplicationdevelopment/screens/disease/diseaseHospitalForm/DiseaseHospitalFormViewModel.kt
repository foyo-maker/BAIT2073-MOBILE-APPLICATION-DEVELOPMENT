package com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseHospitalForm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Hospital
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDiseaseHospitalDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiseaseHospitalFormViewModel : ViewModel(){
    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(
        GetDiseaseHospitalDataService::class.java)
    var createDiseaseHospitalLiveData: MutableLiveData<Disease_Hospital?> = MutableLiveData()

    fun getCreateDiseaseHospitalObservable(): MutableLiveData<Disease_Hospital?> {
        return createDiseaseHospitalLiveData
    }

    fun createDiseaseHospital(diseaseHospital: Disease_Hospital) {
        Log.i("creatingdiseaseHospital","{$diseaseHospital}")
        apiService.createDiseaseHospital(diseaseHospital).enqueue(object :
            Callback<Disease_Hospital?> {
            override fun onResponse(call: Call<Disease_Hospital?>, response: Response<Disease_Hospital?>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    Log.i("res","{$res}")
                    createDiseaseHospitalLiveData.postValue(response.body())
                } else {
                    val res = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("unsuccessful", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createDiseaseHospitalLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Disease_Hospital?>, t: Throwable) {
                Log.e("Error", "Unknown error")
                createDiseaseHospitalLiveData.postValue(null)
            }
        })
    }


}