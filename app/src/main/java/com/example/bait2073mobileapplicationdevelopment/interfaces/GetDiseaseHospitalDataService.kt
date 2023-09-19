package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Hospital
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetDiseaseHospitalDataService {
        @GET("diseaseHospital")
        fun getDiseaseHospitalList(): Call<List<Disease_Hospital>>

        @GET("diseaseHospital/{diseaseHospital_id}")
        @Headers("Accept:application/json","Content-Type:application/json")
        fun getDiseaseHospital(@Path("diseaseHospital_id") diseaseHospital_id: Int): Call<Disease_Hospital>

        @POST("diseaseHospital")
        @Headers("Accept:application/json", "Content-Type:application/json")
        fun createDiseaseHospital(@Body params: Disease_Hospital): Call<Disease_Hospital>

        @PATCH("diseaseHospital/{diseaseHospital_id}")
        @Headers("Accept:application/json", "Content-Type:application/json")
        fun updateDiseaseHospital(@Path("diseaseHospital_id") diseaseHospital_id: Int, @Body params: Disease_Hospital): Call<Disease_Hospital>

        @DELETE("diseaseHospital/{diseaseHospital_id}")
        @Headers("Accept:application/json", "Content-Type:application/json")
        fun deleteDiseaseHospital(@Path("diseaseHospital_id") diseaseHospital_id: Int): Call<Disease_Hospital>

}