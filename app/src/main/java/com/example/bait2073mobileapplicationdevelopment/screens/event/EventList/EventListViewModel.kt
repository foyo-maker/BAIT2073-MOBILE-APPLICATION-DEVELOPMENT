package com.example.bait2073mobileapplicationdevelopment.screens.event.EventList

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bait2073mobileapplicationdevelopment.BaseActivity
import com.example.bait2073mobileapplicationdevelopment.database.EventDatabase
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetEventDataService
import com.example.bait2073mobileapplicationdevelopment.repository.EventRepository
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import com.example.bait2073mobileapplicationdevelopment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import javax.inject.Inject

import kotlinx.coroutines.launch
class EventListViewModel (application: Application): AndroidViewModel(application) {

    private val repository : EventRepository
    val allEvent : LiveData<List<Event>>

    var recyclerListData: MutableLiveData<List<Event?>> = MutableLiveData()
    var deleteEventLiveData: MutableLiveData<Event?> = MutableLiveData()

    init{
        val dao = EventDatabase.getDatabase(application).eventDao()
        repository = EventRepository(dao)
        allEvent = repository.allEvents
    }

    fun getDeleteEventObservable():MutableLiveData<Event?>{
        return deleteEventLiveData
    }

    fun getEventListObserverable(): MutableLiveData<List<Event?>>{
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
                            )
                        )
                    }
                }catch (e: Exception) {
                    Log.e("InsertDataIntoRoomDb", "Error inserting data into Room Database: ${e.message}", e)
                }
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    fun getEvents(){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventDataService::class.java)
        val call = service.getEventList()
            call.enqueue(object : Callback<List<Event>> {

                override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                    Log.e("Event API Error",t.message+" Is here"?:"Event onFailure function ")
                }

                override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                    if(response.isSuccessful){
                        val eventList = response.body()
                        Log.e("Event onResponse","Response successful, code: ${eventList}")

                        if(eventList!=null&&eventList.isNotEmpty()){
                            recyclerListData.postValue(response.body())
                            insertEventDataIntoRoomDb(eventList)
                        }else{
                            Log.e("Error Event API onResponse","onResponse body is empty")
                        }

                    }else{
                        Log.e("Error Event API onResponse","Event onResponse not successful, code: ${response.code()}")

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
                    Log.e("deleteEvent function", "onFailure function")
                    deleteEventLiveData.postValue(response.body())
                } else {
                    Log.e("deleteEvent function", "onFailure function")
                    deleteEventLiveData.postValue(null)
                }
            }
        })

    }


}


