package com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseSymptomForm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Symptom
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDiseaseSymptomDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiseaseSymptomFormViewModel : ViewModel(){
        private val apiService = RetrofitClientInstance.retrofitInstance!!.create(
            GetDiseaseSymptomDataService::class.java)
        var createDiseaseSymptomLiveData: MutableLiveData<Disease_Symptom?> = MutableLiveData()

        fun getCreateDiseaseSymptomObservable(): MutableLiveData<Disease_Symptom?> {
            return createDiseaseSymptomLiveData
        }

        fun createDiseaseSymptom(diseaseSymptom: Disease_Symptom) {
            Log.i("creatingdiseasesymptom","{$diseaseSymptom}")
            apiService.createDiseaseSymptom(diseaseSymptom).enqueue(object : Callback<Disease_Symptom?> {
                override fun onResponse(call: Call<Disease_Symptom?>, response: Response<Disease_Symptom?>) {
                    if (response.isSuccessful) {
                        val res = response.body()
                        Log.i("res","{$res}")
                        createDiseaseSymptomLiveData.postValue(response.body())
                    } else {
                        val res = response.body()
                        Log.i("res","{$res}")
                        val errorBody = response.errorBody()?.string()
                        val responseCode = response.code()
                        val responseMessage = response.message()
                        Log.e("unsuccessful", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                        createDiseaseSymptomLiveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Disease_Symptom?>, t: Throwable) {
                    Log.e("Error", "Unknown error")
                    createDiseaseSymptomLiveData.postValue(null)
                }
            })
        }


}

