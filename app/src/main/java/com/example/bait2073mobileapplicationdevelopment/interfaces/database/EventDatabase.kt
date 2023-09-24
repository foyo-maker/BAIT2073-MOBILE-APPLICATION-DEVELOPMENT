package com.example.bait2073mobileapplicationdevelopment.interfaces.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bait2073mobileapplicationdevelopment.dao.EventDao
import com.example.bait2073mobileapplicationdevelopment.dao.UserDao
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.example.bait2073mobileapplicationdevelopment.entities.User


//@Database(entities = [Event::class], version = 1)
//abstract class EventDatabase : RoomDatabase() {
//
//    abstract fun eventDao(): EventDao
//}

@Database(entities = [Event::class],version = 1,exportSchema = false)
abstract class EventDatabase: RoomDatabase()  {

    companion object{

        var eventDatabase: EventDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): EventDatabase {
            if (eventDatabase == null){
                eventDatabase = Room.databaseBuilder(
                    context,
                    EventDatabase::class.java,
                    "event.db"
                ).build()
            }
            return eventDatabase!!
        }
    }

    abstract fun eventDao(): EventDao
}