package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bait2073mobileapplicationdevelopment.entities.Hospital


@Dao
interface HospitalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHospital(hospital: Hospital )

//    @Update
//    fun updateHospital(hospital: Hospital)

    @Query("SELECT * from hospital WHERE id = :hospitalId")
    fun getHospitalById(hospitalId: Int): LiveData<Hospital>

    @Query("SELECT * FROM hospital ORDER BY hospital_name ASC")
    fun getAllHospital(): LiveData<List<Hospital>>

    @Query("DELETE FROM hospital")
    fun deleteHospital()
}