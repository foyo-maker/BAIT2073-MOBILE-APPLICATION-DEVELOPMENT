package com.example.bait2073mobileapplicationdevelopment.screens.recipe.recipeForm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Recipe
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetRecipeDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeFormViewModel : ViewModel() {

    val apiService = RetrofitClientInstance.retrofitInstance!!.create(GetRecipeDataService::class.java)
    lateinit var createRecipeLiveData: MutableLiveData<Recipe?>
    lateinit var loadRecipeData: MutableLiveData<Recipe?>

    init {
        createRecipeLiveData = MutableLiveData()
        loadRecipeData = MutableLiveData()

    }

    fun getCreateRecipeObservable(): MutableLiveData<Recipe?> {
        return createRecipeLiveData
    }

    fun getLoadRecipeObservable(): MutableLiveData<Recipe?> {
        return loadRecipeData
    }

    fun createRecipe(recipe: Recipe) {
       apiService.createRecipe(recipe).enqueue(object : Callback<Recipe?> {
            override fun onFailure(call: Call<Recipe?>, t: Throwable) {
                Log.e("API Error", t.message ?: "Unknown error")
                createRecipeLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Recipe?>, response: Response<Recipe?>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    createRecipeLiveData.postValue(response.body())
                } else {
                    val res = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("haha", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createRecipeLiveData.postValue(null)
                }
            }
        })
    }

    fun updateRecipe(recipe_id: Int, recipe: Recipe) {

        apiService.updateRecipe(recipe_id,recipe).enqueue(object : Callback<Recipe?> {
            override fun onFailure(call: Call<Recipe?>, t: Throwable) {
                Log.e("error", "failure")
                createRecipeLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Recipe?>, response: Response<Recipe?>) {
                if (response.isSuccessful) {
                    createRecipeLiveData.postValue(response.body())
                } else {
                    val res = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("error", "Response is not successful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createRecipeLiveData.postValue(null)
                }
            }
        })
    }

    fun getRecipeData(recipe_id: Int?) {

        apiService.getRecipe(recipe_id!!).enqueue(object : Callback<Recipe?> {

            override fun onFailure(call: Call<Recipe?>, t: Throwable) {
                Log.e("haha", "wandan")
                loadRecipeData.postValue(null)
            }

            override fun onResponse(call: Call<Recipe?>, response: Response<Recipe?>) {
                if (response.isSuccessful) {
                    val res= response.body()
                    Log.i("haha", "$res")
                    loadRecipeData.postValue(response.body())
                } else {
                    Log.i("haha", "ggla")
                    loadRecipeData.postValue(null)
                }
            }


        })
    }

}