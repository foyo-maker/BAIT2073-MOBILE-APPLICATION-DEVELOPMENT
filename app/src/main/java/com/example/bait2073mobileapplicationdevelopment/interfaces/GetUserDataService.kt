package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.AutenticateEmailUser
import com.example.bait2073mobileapplicationdevelopment.entities.LoginUser
import com.example.bait2073mobileapplicationdevelopment.entities.RegisterUser
import com.example.bait2073mobileapplicationdevelopment.entities.ResetPasswordUser
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateBmiUser
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateGenderUser
import com.example.bait2073mobileapplicationdevelopment.entities.UpdatePasswordUser
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserRating
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

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun deleteUser(@Path("user_id") user_id: Int): Call<User>



    //this three need to be created in server
    //need to create a entity in user call rating, as float
    //   $table->float('your_float_column'); // Define a float column
    @GET("admins")
    fun getAdminList(): Call<List<User>>
    @POST("admins")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun createAdmin(@Body params: User): Call<User>





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

    @PATCH("rating/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updateRate(@Path("user_id") user_id: Int, @Body params: UserRating): Call<UserRating>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updateGender(@Path("user_id") user_id: Int, @Body params: UpdateGenderUser): Call<UpdateGenderUser>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updateBmi(@Path("user_id") user_id: Int, @Body params: UpdateBmiUser): Call<UpdateBmiUser>

    @PATCH("changePassword/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updatePassword(@Path("user_id") user_id: Int, @Body params: UpdatePasswordUser): Call<UpdatePasswordUser>

    @PATCH("resetPassword")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun resetPassword(@Body params: ResetPasswordUser): Call<ResetPasswordUser>

    @POST("emailReset")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun sendEmail(@Body params: AutenticateEmailUser): Call<AutenticateEmailUser>



}