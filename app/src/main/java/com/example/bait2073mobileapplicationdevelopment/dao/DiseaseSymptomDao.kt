package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Symptom


@Dao
interface DiseaseSymptomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiseaseSymptom(diseaseSymptom: Disease_Symptom)

//    @Update
//    fun updateDiseaseSymptom(diseaseSymptom: Disease_Symptom)

    @Query("SELECT * from disease_symptom WHERE disease_id = :diseaseID")
    fun getDiseaseSymptomById(diseaseID: Int): LiveData<Disease_Symptom>

    @Query("SELECT * from disease_symptom WHERE symptom_id = :symptomID")
    fun getSymptomDiseaseById(symptomID: Int): LiveData<Disease_Symptom>

    @Query("SELECT * FROM disease_symptom")
    fun getAllDiseasesSymptom(): LiveData<List<Disease_Symptom>>

    @Query("DELETE FROM disease_symptom")
    fun deleteDiseaseSymptom()

    @Query("DELETE FROM disease_symptom WHERE disease_id =:diseaseID")
    fun deleteOneDiseaseSymptom(diseaseID: Int)

    @Query("DELETE FROM disease_symptom WHERE symptom_id=:symptomID")
    fun deleteOneSymptomDisease(symptomID: Int) //if want return type(know the number row delete) put :Int
}