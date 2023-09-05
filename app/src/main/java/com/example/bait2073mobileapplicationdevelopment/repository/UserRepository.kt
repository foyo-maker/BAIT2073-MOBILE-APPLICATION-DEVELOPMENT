package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.UserDao
import com.example.bait2073mobileapplicationdevelopment.entities.User

class UserRepository (private val userDao:UserDao){

    var allUsers : LiveData<List<User>> = userDao.getAllUsers()
    suspend fun insert(user: User){
        userDao.insertUser(user)
    }


//    suspend fun delete(note: Note){
//        noteDao.delete(note)
//    }
//
//    suspend fun update(note: Note){
//        noteDao.update(note.id,note.title,note.note)
//    }
}