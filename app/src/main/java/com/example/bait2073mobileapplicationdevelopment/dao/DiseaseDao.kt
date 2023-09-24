package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bait2073mobileapplicationdevelopment.entities.Disease

@Dao
interface DiseaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDisease(disease: Disease)

//    @Update
//    fun updateDisease(disease: Disease)

    @Query("SELECT * from disease WHERE id = :diseaseID")
    fun getDiseaseById(diseaseID: Int): LiveData<Disease>

    @Query("SELECT * FROM disease ORDER BY disease_name ASC")
    fun getAllDiseases(): LiveData<List<Disease>>

    @Query("DELETE FROM disease")
    fun deleteDisease()
}