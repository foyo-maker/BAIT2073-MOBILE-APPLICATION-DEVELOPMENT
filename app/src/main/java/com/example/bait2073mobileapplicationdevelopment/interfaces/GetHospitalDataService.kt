package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.Hospital
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetHospitalDataService {
    @GET("hospitals")
    fun getHospitalList(): Call<List<Hospital>>

    @GET("hospitals/{hospital_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getHospital(@Path("hospital_id") hospital_id: Int): Call<Hospital>

    @POST("hospitals")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun createHospital(@Body params: Hospital): Call<Hospital>

    @PATCH("hospitals/{hospital_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun updateHospital(@Path("hospital_id") hospital_id: Int, @Body params: Hospital): Call<Hospital>

    @DELETE("hospitals/{hospital_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun deleteHospital(@Path("hospital_id") hospital_id: Int): Call<Hospital>
}