package com.example.bait2073mobileapplicationdevelopment.interfaces

import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GetUserPLanListService {

    @GET("userPlanList")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUserPlanList(): Call<List<UserPlanList>>

    @GET("userPlanList/{user_plan_id}")
    fun getUserPlanListByUserPlanId(@Path("user_plan_id") userPlanId: Int?): Call<List<UserPlanList>>

    @POST("userPlanList")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun insertUserPlanList(@Body userPlanList: UserPlanList): Call<UserPlanList>

    @DELETE("deleteUserPlanList/{user_plan_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun deleteUserPlanListByUserPlanId(@Path("user_plan_id") userPlanId: Int): Call<UserPlanList>
    @GET("checkUserPlanListEmpty/{userPlanId}")
    fun checkUserPlanListEmpty(@Path("userPlanId") userPlanId: Int): Call<Boolean>
    @DELETE("deleteUserPlanList/{id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun deleteUserPlanListById(@Path("id") id: Int?): Call<UserPlanList>

    @PUT("userPlanList/{user_plan_id}")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c"
    )
    fun updateUserPlanListByUserPlanId(
        @Path("user_plan_id") userPlanId: Int,
        @Body updatedUserPlan: UserPlanList
    ): Call<UserPlanList>

}