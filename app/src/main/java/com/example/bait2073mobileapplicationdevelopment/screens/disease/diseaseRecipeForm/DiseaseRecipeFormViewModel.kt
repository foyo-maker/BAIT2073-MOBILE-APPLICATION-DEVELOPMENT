package com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseRecipeForm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Recipe
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDiseaseRecipeDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiseaseRecipeFormViewModel : ViewModel(){
    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(
        GetDiseaseRecipeDataService::class.java)
    var createDiseaseRecipeLiveData: MutableLiveData<Disease_Recipe?> = MutableLiveData()

    fun getCreateDiseaseRecipeObservable(): MutableLiveData<Disease_Recipe?> {
        return createDiseaseRecipeLiveData
    }

    fun createDiseaseRecipe(diseaseRecipe: Disease_Recipe) {
        Log.i("creatingdiseasesymptom","{$diseaseRecipe}")
        apiService.createDiseaseRecipe(diseaseRecipe).enqueue(object :
            Callback<Disease_Recipe?> {
            override fun onResponse(call: Call<Disease_Recipe?>, response: Response<Disease_Recipe?>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    Log.i("res","{$res}")
                    createDiseaseRecipeLiveData.postValue(response.body())
                } else {
                    val res = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("unsuccessful", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createDiseaseRecipeLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Disease_Recipe?>, t: Throwable) {
                Log.e("Error", "Unknown error")
                createDiseaseRecipeLiveData.postValue(null)
            }
        })
    }


}

