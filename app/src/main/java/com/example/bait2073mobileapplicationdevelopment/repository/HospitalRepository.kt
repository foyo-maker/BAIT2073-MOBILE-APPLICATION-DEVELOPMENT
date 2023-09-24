package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.HospitalDao
import com.example.bait2073mobileapplicationdevelopment.entities.Hospital
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom

class HospitalRepository (private val hospitalDao: HospitalDao) {

    var allHospitals: LiveData<List<Hospital>> = hospitalDao.getAllHospital()

    fun retrieve(): LiveData<List<Hospital>> {
        return hospitalDao.getAllHospital()
    }

    suspend fun insert(hospital: Hospital) {
        hospitalDao.insertHospital(hospital)
    }
    suspend fun getHospitalById(hospitalId: Int): LiveData<Hospital> {
        return hospitalDao.getHospitalById(hospitalId)
    }
    suspend fun deleteAll(){
        hospitalDao.deleteHospital()
    }

}