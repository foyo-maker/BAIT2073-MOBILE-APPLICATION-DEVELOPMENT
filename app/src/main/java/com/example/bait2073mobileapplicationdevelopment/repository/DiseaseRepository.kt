package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.DiseaseDao
import com.example.bait2073mobileapplicationdevelopment.entities.Disease

class DiseaseRepository  (private val diseaseDao:DiseaseDao) {

    var allDiseases: LiveData<List<Disease>> = diseaseDao.getAllDiseases()

    suspend fun insert(disease: Disease) {
        diseaseDao.insertDisease(disease)
    }
}