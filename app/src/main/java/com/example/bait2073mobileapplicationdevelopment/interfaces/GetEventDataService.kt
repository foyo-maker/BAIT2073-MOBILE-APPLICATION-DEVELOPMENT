package com.example.bait2073mobileapplicationdevelopment.interfaces


import com.example.bait2073mobileapplicationdevelopment.entities.Event

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GetEventDataService {


    @GET("events")
    fun getEventList(): Call<List<Event>>

    @GET("events/{event_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getEvent(@Path("event_id") event_id: Int): Call<Event>

    @POST("events")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun addEvent(@Body params: Event): Call<Event>

    @PATCH("events/{event_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun updateEvent(@Path("event_id") event_id: Int, @Body params: Event): Call<Event>


    @DELETE("events/{event_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun deleteEvent(@Path("event_id") event_id: Int): Call<Event>

}