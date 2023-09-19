package com.example.bait2073mobileapplicationdevelopment.screens.admin.recipeList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Recipe
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetRecipeDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RecipeListViewModel: ViewModel() {

    private val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetRecipeDataService::class.java)
    private var recipeListMut = MutableLiveData<List<Recipe>?>()
    val recipeList: MutableLiveData<List<Recipe>?> get() = recipeListMut

    fun getRecipeList() {
        apiService.getRecipeList().enqueue(object : Callback<List<Recipe>> {

            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful) {
                    val recipeLists = response.body()
//                        Log.e("gg", "Response not successful, code: ${recipeLists}")
                    if (!recipeLists.isNullOrEmpty()) {
                        // Insert the recipe data into the Room Database
                        recipeListMut.postValue(response.body())
                    } else {
                        // Handle the case where the response is empty
                        Log.e("API Response", "Response body is empty")
                    }
                } else {
                    // Handle the case where the API response is not successful
                    Log.e("API Response", "Response not successful, code: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                // Handle network or API errors
                Log.e("API Error", "Failed to fetch data: ${t.message}", t)
            }

        })
    }
}
