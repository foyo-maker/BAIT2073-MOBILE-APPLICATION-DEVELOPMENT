package com.example.bait2073mobileapplicationdevelopment.screens.eventParticipants.EventParticipantsParticipants

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.example.bait2073mobileapplicationdevelopment.entities.EventParticipants
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetEventDataService
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetEventParticipantsDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventParticipantsViewModel : ViewModel(){

lateinit var createNewEventParticipantsLiveData: MutableLiveData<EventParticipants?>
lateinit var loadEventParticipantsData: MutableLiveData<EventParticipants?>

    var recyclerListData: MutableLiveData<List<Event?>?> = MutableLiveData()
    var deleteEventLiveData: MutableLiveData<Event?> = MutableLiveData()

init {
    createNewEventParticipantsLiveData = MutableLiveData()
    loadEventParticipantsData = MutableLiveData()
}

    val eventParticipantsCount = MutableLiveData<Int?>()


fun getCreateNewEventParticipantsObservable(): MutableLiveData<EventParticipants?> {
    return createNewEventParticipantsLiveData
}


fun getLoadEventParticipantsObservable(): MutableLiveData<EventParticipants?> {
    return loadEventParticipantsData
}

    fun getEventListObserverable(): MutableLiveData<List<Event?>?> {
        return recyclerListData
    }

    fun getEventPartSizeObserverable(): MutableLiveData<Int?> {
        return eventParticipantsCount
    }

    fun createEventParticipants(eventParticipants: EventParticipants, callback: (Boolean) -> Unit) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventParticipantsDataService::class.java)
        val call = service.addEventParticipants(eventParticipants)
        call.enqueue(object : Callback<EventParticipants?> {
            override fun onFailure(call: Call<EventParticipants?>, t: Throwable) {
                Log.e("createEventParticipants onFailure", "createEventParticipants onFailure ${t}")
                callback(false)
            }

            override fun onResponse(call: Call<EventParticipants?>, response: Response<EventParticipants?>) {
                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.i("createEventParticipants onResponse isSuccessful", "$resposne")
                    callback(true)
                } else {
                    val resposne = response.body()
                    val errorBody = response.errorBody()?.string()
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    Log.e("createEventParticipants onResponse not successful", "Response is Notsuccessful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
                    callback(false)
                }
            }
        })
    }

//fun createEventParticipants(eventParticipants: EventParticipants) {
//    val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventParticipantsDataService::class.java)
//    val call = service.addEventParticipants(eventParticipants)
//    call.enqueue(object : Callback<EventParticipants?> {
//        override fun onFailure(call: Call<EventParticipants?>, t: Throwable) {
//            Log.e("createEventParticipants onFailure", "createEventParticipants onFailure ${t}")
//            createNewEventParticipantsLiveData.postValue(null)
//        }
//
//        override fun onResponse(call: Call<EventParticipants?>, response: Response<EventParticipants?>) {
//            if (response.isSuccessful) {
//                val resposne = response.body()
//                Log.i("createEventParticipants onResponse isSuccessful", "$resposne")
//                createNewEventParticipantsLiveData.postValue(response.body())
//            } else {
//                val resposne = response.body()
//                val errorBody = response.errorBody()?.string()
//                val responseCode = response.code()
//                val responseMessage = response.message()
//                Log.e("createEventParticipants onResponse not successful", "Response is Notsuccessful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
//                createNewEventParticipantsLiveData.postValue(null)
//            }
//        }
//    })
//}

    fun getEventsPart(user_id : Int?) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventParticipantsDataService::class.java)
        val call = service.getEventUserParticipantsList(user_id)
        call.enqueue(object : Callback<List<Event>> {
            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                Log.e("Event API Error", "API call failed: ${t.message}")
                recyclerListData.postValue(null)
            }

            override fun onResponse(
                call: Call<List<Event>>,
                response: Response<List<Event>>
            ) {

                if (response.isSuccessful) {
                    val resposne = response.body()
                    Log.e("What","${response.body()}")
                    recyclerListData.postValue(response.body())
                } else {
                    Log.i("getEventData onResponse notSuccessful", "ggla")
                    recyclerListData.postValue(null)
                }
            }
        })

 }


    fun getEventsPartSize(event_id: Int) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventParticipantsDataService::class.java)
        val call = service.getEventParticipantsCount(event_id)
        call.enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("Event API Error", "API call failed: ${t.message}")
                eventParticipantsCount.postValue(null) // Post null to indicate failure
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    val count = response.body()
                    Log.e("AA","${count}")
                    eventParticipantsCount.postValue(count) // Post the count value
                } else {
                    Log.e("Event API Error", "API response not successful, code: ${response.code()}")
                    eventParticipantsCount.postValue(null) // Post null to indicate failure
                }
            }
        })
    }




    fun deleteEventPart(event_id : Int, user_id : Int){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventParticipantsDataService::class.java)
        val call = service.deleteEventParticipants(event_id,user_id)
        call.enqueue(object : Callback<Event?> {

            override fun onFailure(call: Call<Event?>, t: Throwable) {
                Log.e("deleteEvent function", "onFailure function")
                deleteEventLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Event?>, response: Response<Event?>) {
                if(response.isSuccessful) {

                    deleteEventLiveData.postValue(response.body())
//                    removeEventFromLocalDatabase()
                } else {
                    Log.e("deleteEvent function", "Delete not successful")
                    deleteEventLiveData.postValue(response.body())

                }
            }
        })
    }



}




//    fun updateEventParticipants(eventParticipants_id: Int, eventParticipants: EventParticipants) {
//    val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventParticipantsDataService::class.java)
//    val call = service.updateEventParticipants(eventParticipants_id, eventParticipants)
//    call.enqueue(object : Callback<EventParticipants?> {
//        override fun onFailure(call: Call<EventParticipants?>, t: Throwable) {
//            Log.e("updateEventParticipants onFailure", "failure")
//            createNewEventParticipantsLiveData.postValue(null)
//        }
//
//        override fun onResponse(call: Call<EventParticipants?>, response: Response<EventParticipants?>) {
//            if (response.isSuccessful) {
//                createNewEventParticipantsLiveData.postValue(response.body())
//            } else {
//
//                val resposne = response.body()
//                val errorBody = response.errorBody()?.string()
//                val responseCode = response.code()
//                val responseMessage = response.message()
//                Log.e("updateEventParticipants onResponse not successful", "Response is Notsuccessful. Code: $responseCode, Message: $responseMessage, Error Body: $errorBody")
//                createNewEventParticipantsLiveData.postValue(null)
//            }
//        }
//    })
//}



//fun getEventParticipantsData(eventParticipants_id: Int?) {
//    val service = RetrofitClientInstance.retrofitInstance!!.create(GetEventParticipantsDataService::class.java)
//    val call = service.getEventParticipantsList()
//    call.enqueue(object : Callback<EventParticipants?> {
//
//        override fun onFailure(call: Call<EventParticipants?>, t: Throwable) {
//            Log.e("getEventParticipantsData onFailure", "wandan")
//            loadEventParticipantsData.postValue(null)
//        }
//
//        override fun onResponse(call: Call<EventParticipants?>, response: Response<EventParticipants?>) {
//            if (response.isSuccessful) {
//                val resposne = response.body()
//                Log.i("getEventParticipantsData onResponse isSuccessful", "$resposne")
//                loadEventParticipantsData.postValue(response.body())
//            } else {
//                Log.i("getEventParticipantsData onResponse notSuccessful", "ggla")
//                loadEventParticipantsData.postValue(null)
//            }
//        }
//
//
//    })
//}

//}