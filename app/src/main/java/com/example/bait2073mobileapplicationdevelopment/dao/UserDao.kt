package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bait2073mobileapplicationdevelopment.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY id DESC")
     fun getAllUsers() : LiveData<List<User>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUser(user: User)

    @Query("DELETE FROM user")
     fun clearDb()

//    @Query("SELECT * FROM user WHERE id = :userId")
//    suspend fun getSpecificUser(userId:String) : List<UserLists>

    //@Query("SELECT *
}