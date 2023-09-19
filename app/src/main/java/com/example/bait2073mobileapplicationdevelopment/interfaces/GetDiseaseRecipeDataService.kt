package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Recipe
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetDiseaseRecipeDataService {
    @GET("diseaseRecipe")
    fun getDiseaseRecipeList(): Call<List<Disease_Recipe>>

    @GET("diseaseRecipe/{diseaseRecipe_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getDiseaseRecipe(@Path("diseaseRecipe_id") diseaseRecipe_id: Int): Call<Disease_Recipe>

    @POST("diseaseRecipe")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun createDiseaseRecipe(@Body params: Disease_Recipe): Call<Disease_Recipe>

    @PATCH("diseaseRecipe/{diseaseRecipe_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun updateDiseaseRecipe(@Path("diseaseRecipe_id") diseaseRecipe_id: Int, @Body params: Disease_Recipe): Call<Disease_Recipe>

    @DELETE("diseaseRecipe/{diseaseRecipe_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun deleteDiseaseRecipe(@Path("diseaseRecipe_id") diseaseRecipe_id: Int): Call<Disease_Recipe>
}