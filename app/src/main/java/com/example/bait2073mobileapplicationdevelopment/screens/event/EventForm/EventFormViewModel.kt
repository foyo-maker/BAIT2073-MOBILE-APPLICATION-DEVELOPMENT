package com.example.bait2073mobileapplicationdevelopment.screens.event.EventForm

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.database.HealthyLifeDatabase
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetEventDataService
import com.example.bait2073mobileapplicationdevelopment.repository.EventRepository
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventFormViewModel : ViewModel() {

    lateinit var createNewEventLiveData: MutableLiveData<Event?>
    lateinit var loadEventData: MutableLiveData<Event?>



    init {
        createNewEventLiveData = MutableLiveData()
        loadEventData = MutableLiveData()
    }



    fun getCreateNewEventObservable(): MutableLiveData<Event?> {
        return createNewEventLiveData
    }


    fun getLoadEventObservable(): MutableLiveData<Event?> {
        return loadEventData
    }

    fun createEvent(event: Event) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventDataService::class.java)
        val call = service.addEvent(event)
        call.enqueue(object : Callback<Event?> {
            override fun onFailure(call: Call<Event?>, t: Throwable) {
                Log.e("createEvent onFailure", "createEvent onFailure ${t}")
                createNewEventLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Event?>, response: Response<Event?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.i("createEvent onResponse isSuccessful", "$resposne")
                    createNewEventLiveData.postValue(response.body())
                } else {
                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("createEvent onResponse not successful", "Response is Notsuccessful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createNewEventLiveData.postValue(null)
                }
            }
        })
    }



    fun updateEvent(event_id: Int, event: Event) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventDataService::class.java)
        val call = service.updateEvent(event_id, event)
        call.enqueue(object : Callback<Event?> {
            override fun onFailure(call: Call<Event?>, t: Throwable) {
                Log.e("updateEvent onFailure", "failure")
                createNewEventLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Event?>, response: Response<Event?>) {
                if (response.isSuccessful) {
                    createNewEventLiveData.postValue(response.body())
                } else {

                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("updateEvent onResponse not successful", "Response is Notsuccessful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    createNewEventLiveData.postValue(null)
                }
            }
        })
    }


    fun getEventData(event_id: Int?) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventDataService::class.java)
        val call = service.getEvent(event_id!!)
        call.enqueue(object : Callback<Event?> {

            override fun onFailure(call: Call<Event?>, t: Throwable) {
                Log.e("getEventData onFailure", "wandan")
                loadEventData.postValue(null)
            }

            override fun onResponse(call: Call<Event?>, response: Response<Event?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.i("getEventData onResponse isSuccessful", "$resposne")
                    loadEventData.postValue(response.body())
                } else {
                    Log.i("getEventData onResponse notSuccessful", "ggla")
                    loadEventData.postValue(null)
                }
            }


        })
    }

}