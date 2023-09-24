package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlan
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetWorkoutDataService {

    @GET("workouts/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getWorkoutListByUserID(@Path("user_id") user_id: Int): Call<List<Workout>>
    @GET("workouts")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun getWorkoutList(): Call<List<Workout>>

    @POST("workouts")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun createWorkout(@Body params: Workout): Call<Workout>

    @DELETE("workouts/{workout_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun deleteWorkout(@Path("workout_id") workout_id: Int): Call<Workout>

    @PATCH("workouts/{workout_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updateWorkout(@Path("workout_id") workout_id: Int, @Body params: Workout): Call<Workout>

    @GET("workouts/{workout_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getWorkout(@Path("workout_id") workout_id: Int): Call<Workout>




}