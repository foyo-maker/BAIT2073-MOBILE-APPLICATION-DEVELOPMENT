package com.example.bait2073mobileapplicationdevelopment.screens.event.UserEventList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bait2073mobileapplicationdevelopment.database.HealthyLifeDatabase
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetEventDataService
import com.example.bait2073mobileapplicationdevelopment.repository.EventRepository
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserEventListViewModel (application: Application): AndroidViewModel(application) {

    private val repository : EventRepository
    val allEvent : LiveData<List<Event>>
    val recyclerListDataDao: LiveData<List<Event>>

    var recyclerListData: MutableLiveData<List<Event?>> = MutableLiveData()
    var deleteEventLiveData: MutableLiveData<Event?> = MutableLiveData()

    init{
        val dao = HealthyLifeDatabase.getDatabase(application).eventDao()
        repository = EventRepository(dao)
        allEvent = repository.allEvents
        recyclerListDataDao = repository.retrieve()
    }

    fun getLocalDao(){
        recyclerListDataDao.observeForever { newData ->
            recyclerListData.postValue(newData)
            Log.e("Error Event API onResponse", "Local Database ${newData}")
        }
    }



    fun getDeleteEventObservable(): MutableLiveData<Event?> {
        return deleteEventLiveData
    }

    fun getEventListObserverable(): MutableLiveData<List<Event?>> {
        return recyclerListData
    }


    fun insertEvent(event : Event)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(event)
    }

    fun insertEventDataIntoRoomDb(events: List<Event>) {

        viewModelScope.launch {
            this.let {

                try {
                    for (event in events) {
                        Log.d("InsertEventDataIntoRoomDb", "Inserting event with ID: ${event.id}")
                        insertEvent(
                            Event(
                                id = event.id,
                                title = event.title ?: "",
                                details = event.details ?: "",
                                image = event.image ?: "",
                                date = event.date ?: "",
                                address = event.address ?: "",
                                "",
                                0
                            )
                        )
                    }
                }catch (e: Exception) {
                    Log.e("InsertDataIntoRoomDb", "Error inserting data into Room Database: ${e.message}", e)
                }
            }
        }

    }



    fun getEvents() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventDataService::class.java)
        val call = service.getEventList()
        call.enqueue(object : Callback<List<Event>> {
            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                Log.e("Event API Error", "API call failed: ${t.message}")

            }

            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                if (response.isSuccessful) {
                    val eventList = response.body()
                    Log.e("Event onResponse", "Response successful, code: ${eventList}")

                    if (eventList != null && eventList.isNotEmpty()) {
                        recyclerListData.postValue(response.body())
                        insertEventDataIntoRoomDb(eventList)
                        Log.e("Event API Error", "LOCAL WORK Data retrieved from API")
                    } else {
                        Log.e("Error Event API onResponse", "API response body is empty")
                    }
                } else {
                    Log.e("Error Event API onResponse", "API response not successful, code: ${response.code()}")
                }
            }
        })
    }

    fun deleteEvent(event: Event){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventDataService::class.java)
        val call = service.deleteEvent(event.id?:0)
        call.enqueue(object : Callback<Event?> {

            override fun onFailure(call: Call<Event?>, t: Throwable) {
                Log.e("deleteEvent function", "onFailure function")
                deleteEventLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Event?>, response: Response<Event?>) {
                if(response.isSuccessful) {
                    Log.e("deleteEvent function", "Delete successful")
                    deleteEventLiveData.postValue(response.body())
                    removeEventFromLocalDatabase()
                } else {
                    Log.e("deleteEvent function", "Delete not successful")
                    deleteEventLiveData.postValue(response.body())

                }
            }
        })
    }

    private fun removeEventFromLocalDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }


}


