package com.example.bait2073mobileapplicationdevelopment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bait2073mobileapplicationdevelopment.database.HealthyLifeDatabase
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel  (application: Application): AndroidViewModel(application){
    private val repository : UserRepository
    val allusers : LiveData<List<User>>
    init{
        val dao = HealthyLifeDatabase.getDatabase(application).userDao()
        repository = UserRepository(dao)
        allusers = repository.allUsers
    }
//
//    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
//        repository.delete(note)
//    }
    fun insertUser(user : User)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(user)
    }


//    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
//        repository.update(note)
//    }


}