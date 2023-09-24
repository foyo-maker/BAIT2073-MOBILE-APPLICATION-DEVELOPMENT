package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom

@Dao
interface SymptomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSymptom(symptom: Symptom)

//    @Update
//    fun updateSymptom(symptom: Symptom)

    @Query("SELECT * from symptom WHERE id = :symptomId")
    fun getSymptomById(symptomId: Int): LiveData<Symptom>

    @Query("SELECT * FROM symptom ORDER BY symptom_name ASC")
    fun getAllSymptom(): LiveData<List<Symptom>>

    @Query("DELETE FROM symptom")
    fun deleteSymptom()
}