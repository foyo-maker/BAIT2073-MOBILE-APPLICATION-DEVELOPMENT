package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.Disease
//import com.example.bait2073mobileapplicationdevelopment.entities.LoginUser (function inside entities.user laide)

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetDiseaseDataService {

    @GET("diseases")
    fun getDiseaseList(): Call<List<Disease>>

    @GET("diseases/{disease_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getDisease(@Path("disease_id") disease_id: Int?): Call<Disease>

    @POST("diseases")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun createDisease(@Body params: Disease): Call<Disease>

    @PATCH("diseases/{disease_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun updateDisease(@Path("disease_id") disease_id: Int, @Body params: Disease): Call<Disease>

    @DELETE("diseases/{disease_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun deleteDisease(@Path("disease_id") disease_id: Int): Call<Disease>
}