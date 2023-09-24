package com.example.bait2073mobileapplicationdevelopment.screens.symptom.symptomForm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetSymptomDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SymptomFormViewModel : ViewModel(){
    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetSymptomDataService::class.java)
    var createSymptomLiveData: MutableLiveData<Symptom?> = MutableLiveData()
    var loadSymptomData: MutableLiveData<Symptom?> = MutableLiveData()

    fun getCreateSymptomObservable(): MutableLiveData<Symptom?> {
        return createSymptomLiveData
    }
    fun getLoadSymptomObservable(): MutableLiveData<Symptom?> {
        return loadSymptomData
    }


    fun createSymptom(symptom: Symptom) {
        apiService.createSymptom(symptom).enqueue(object : Callback<Symptom?> {
            override fun onResponse(call: Call<Symptom?>, response: Response<Symptom?>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    createSymptomLiveData.postValue(response.body())
                } else {
                    val res = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("unsuccessful", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createSymptomLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Symptom?>, t: Throwable) {
                Log.e("Error", "Unknown error")
                createSymptomLiveData.postValue(null)
            }
        })
    }

    fun getSymptomData(symptom_id: Int?) {
        apiService.getSymptom(symptom_id!!).enqueue(object : Callback<Symptom?> {

            override fun onResponse(call: Call<Symptom?>, response: Response<Symptom?>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.i("haha", "$result")
                    loadSymptomData.postValue(response.body())
                } else {
                    Log.i("haha", "not workinggg")
                    loadSymptomData.postValue(null)
                }
            }
            override fun onFailure(call: Call<Symptom?>, t: Throwable) {
                Log.e("haha", "failed")
                loadSymptomData.postValue(null)
            }


        })
    }

    fun updateSymptom(symptom_id: Int, symptom: Symptom) {
        apiService.updateSymptom(symptom_id, symptom).enqueue(object : Callback<Symptom?> {
            override fun onResponse(call: Call<Symptom?>, response: Response<Symptom?>) {
                if (response.isSuccessful) {
                    createSymptomLiveData.postValue(response.body())
                } else {
                    val result = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("error", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createSymptomLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<Symptom?>, t: Throwable) {
                Log.e("error", "failure")
                createSymptomLiveData.postValue(null)
            }

        })
    }

}
