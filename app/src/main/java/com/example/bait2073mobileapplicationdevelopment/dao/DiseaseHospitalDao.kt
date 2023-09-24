package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Hospital

@Dao
interface DiseaseHospitalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiseaseHospital(diseaseHospital: Disease_Hospital)

//    @Update
//    fun updateDiseaseSHospital(diseaseHospital: Disease_Hospital)

    @Query("SELECT * from disease_hospital WHERE id = :diseaseHospitalId")
    fun getDiseaseHospitalById(diseaseHospitalId: Int): LiveData<Disease_Hospital>

//    @Query("SELECT * from disease_hospital WHERE disease_id = :diseaseID")
//    fun getDiseaseHospitalById(diseaseID: Int): LiveData<Disease_Hospital>
//
//    @Query("SELECT * from disease_hospital WHERE hospital_id = :hospitalID")
//    fun getSymptomDiseaseById(hospitalID: Int): LiveData<Disease_Hospital>

    @Query("SELECT * FROM disease_hospital")
    fun getAllDiseasesHospital(): LiveData<List<Disease_Hospital>>

    @Query("DELETE FROM disease_hospital")
    fun deleteDiseaseHospital()

    @Query("DELETE FROM disease_hospital WHERE disease_id =:diseaseId")
    fun deleteOneDiseaseHospital(diseaseId: Int)

    @Query("DELETE FROM disease_hospital WHERE hospital_id=:hospitalId")
    fun deleteOneHospitalDisease(hospitalId: Int) //if want return type(know the number row delete) put :Int
}