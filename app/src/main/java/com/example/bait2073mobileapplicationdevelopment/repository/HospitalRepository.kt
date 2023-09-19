package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.HospitalDao
import com.example.bait2073mobileapplicationdevelopment.entities.Hospital

class HospitalRepository (private val hospitalDao: HospitalDao) {

    var allHospitals: LiveData<List<Hospital>> = hospitalDao.getAllHospital()

    suspend fun insert(hospital: Hospital) {
        hospitalDao.insertHospital(hospital)
    }
}