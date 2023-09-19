package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Symptom
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetDiseaseSymptomDataService {
    @GET("diseaseSymptom")
    fun getDiseaseSymptomList(): Call<List<Disease_Symptom>>

    @GET("diseaseSymptom/{diseaseSymptom_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getDiseaseSymptom(@Path("diseaseSymptom_id") diseaseSymptom_id: Int): Call<Disease_Symptom>

    @POST("diseaseSymptom")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun createDiseaseSymptom(@Body params: Disease_Symptom): Call<Disease_Symptom>

    @PATCH("diseaseSymptom/{diseaseSymptom_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun updateDiseaseSymptom(@Path("diseaseSymptom_id") diseaseSymptom_id: Int, @Body params: Disease_Symptom): Call<Disease_Symptom>

    @DELETE("diseases/{diseaseSymptom_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun deleteDiseaseSymptom(@Path("diseaseSymptom_id") diseaseSymptom_id: Int): Call<Disease_Symptom>
}