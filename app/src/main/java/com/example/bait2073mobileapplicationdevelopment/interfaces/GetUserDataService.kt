package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.LoginUser
import com.example.bait2073mobileapplicationdevelopment.entities.RegisterUser
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateGenderUser
import com.example.bait2073mobileapplicationdevelopment.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetUserDataService {


    @GET("users")
    fun getUserList(): Call<List<User>>

    //https://gorest.co.in/public-api/users/121
    @GET("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUser(@Path("user_id") user_id: Int): Call<User>


    @POST("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun createUser(@Body params: User): Call<User>

    @POST("register")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun registerUser(@Body params: RegisterUser): Call<RegisterUser>


    @POST("login")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun authenticate(@Body params: LoginUser): Call<LoginUser>
    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updateUser(@Path("user_id") user_id: Int, @Body params: User): Call<User>


    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updateGender(@Path("user_id") user_id: Int, @Body params: UpdateGenderUser): Call<UpdateGenderUser>
    @DELETE("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun deleteUser(@Path("user_id") user_id: Int): Call<User>
}