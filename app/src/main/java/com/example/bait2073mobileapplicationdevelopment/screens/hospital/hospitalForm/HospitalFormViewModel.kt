package com.example.bait2073mobileapplicationdevelopment.screens.hospital.hospitalForm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Hospital
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetHospitalDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalFormViewModel : ViewModel(){
    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetHospitalDataService::class.java)
    var createHospitalLiveData: MutableLiveData<Hospital?> = MutableLiveData()
    var loadHospitalData: MutableLiveData<Hospital?> = MutableLiveData()

    fun getCreateHospitalObservable(): MutableLiveData<Hospital?> {
        return createHospitalLiveData
    }
    fun getLoadHospitalObservable(): MutableLiveData<Hospital?> {
        return loadHospitalData
    }


    fun createHospital(hospital: Hospital) {
        apiService.createHospital(hospital).enqueue(object : Callback<Hospital?> {
            override fun onResponse(call: Call<Hospital?>, response: Response<Hospital?>) {
                if (response.isSuccessful) {
                    val results = response.body()
                    createHospitalLiveData.postValue(response.body())
                } else {
                    val results = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("unsuccessful", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createHospitalLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Hospital?>, t: Throwable) {
                Log.e("Error", "Unknown error")
                createHospitalLiveData.postValue(null)
            }
        })
    }

    fun getHospitalData(hospital_id: Int?) {
        apiService.getHospital(hospital_id!!).enqueue(object : Callback<Hospital?> {

            override fun onResponse(call: Call<Hospital?>, response: Response<Hospital?>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.i("haha", "$result")
                    loadHospitalData.postValue(response.body())
                } else {
                    Log.i("haha", "not workinggg")
                    loadHospitalData.postValue(null)
                }
            }
            override fun onFailure(call: Call<Hospital?>, t: Throwable) {
                Log.e("haha", "failed")
                loadHospitalData.postValue(null)
            }
        })
    }

    fun updateHospital(hospital_id: Int, hospital: Hospital) {
        apiService.updateHospital(hospital_id, hospital).enqueue(object : Callback<Hospital?> {
            override fun onResponse(call: Call<Hospital?>, response: Response<Hospital?>) {
                if (response.isSuccessful) {
                    createHospitalLiveData.postValue(response.body())
                } else {
                    val result = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("error", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createHospitalLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<Hospital?>, t: Throwable) {
                Log.e("error", "failure")
                createHospitalLiveData.postValue(null)
            }

        })
    }

}