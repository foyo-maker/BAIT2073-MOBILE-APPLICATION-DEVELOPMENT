package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.Symptom
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetSymptomDataService {

        @GET("symptoms")
        fun getSymptomList(): Call<List<Symptom>>

        @GET("symptoms/{symptom_id}")
        @Headers("Accept:application/json","Content-Type:application/json")
        fun getSymptom(@Path("symptom_id") symptom_id: Int?): Call<Symptom>

        @POST("symptoms")
        @Headers("Accept:application/json", "Content-Type:application/json")
        fun createSymptom(@Body params: Symptom): Call<Symptom>

        @PATCH("symptoms/{symptom_id}")
        @Headers("Accept:application/json", "Content-Type:application/json")
        fun updateSymptom(@Path("symptom_id") symptom_id: Int, @Body params: Symptom): Call<Symptom>

        @DELETE("symptoms/{symptom_id}")
        @Headers("Accept:application/json", "Content-Type:application/json")
        fun deleteSymptom(@Path("symptom_id") symptom_id: Int): Call<Symptom>
}