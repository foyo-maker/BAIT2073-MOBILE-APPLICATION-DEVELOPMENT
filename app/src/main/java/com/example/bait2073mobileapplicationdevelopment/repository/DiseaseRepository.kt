package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.DiseaseDao
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom


class DiseaseRepository  (private val diseaseDao:DiseaseDao) {

    var allDiseases: LiveData<List<Disease>> = diseaseDao.getAllDiseases()

    fun retrieve(): LiveData<List<Disease>> {
        return diseaseDao.getAllDiseases()
    }
    suspend fun insert(disease: Disease) {
        diseaseDao.insertDisease(disease)
    }
    suspend fun getDiseaseById(diseaseId: Int): LiveData<Disease> {
        return diseaseDao.getDiseaseById(diseaseId)
    }

    suspend fun deleteAll(){
        diseaseDao.deleteDisease()
    }
}