package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.UserDao
import com.example.bait2073mobileapplicationdevelopment.entities.User

class UserRepository (private val userDao:UserDao){

    var allUsers : LiveData<List<User>> = userDao.getAllUsers()


    suspend fun insert(user: User){
        userDao.insertUser(user)
    }

    suspend fun getUserById(userId: Int): LiveData<User> {
        return userDao.getUserById(userId)
    }


}