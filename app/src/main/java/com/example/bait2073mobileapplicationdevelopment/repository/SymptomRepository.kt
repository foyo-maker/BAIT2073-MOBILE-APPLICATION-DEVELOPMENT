package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.SymptomDao
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom

class SymptomRepository (private val symptomDao: SymptomDao) {

    var allSymptoms: LiveData<List<Symptom>> = symptomDao.getAllSymptom()

    suspend fun insert(symptom: Symptom) {
        symptomDao.insertSymptom(symptom)
    }

   fun retrieve(): LiveData<List<Symptom>> {
        return symptomDao.getAllSymptom()
    }

    suspend fun getSymptomById(symptomId: Int): LiveData<Symptom> {
        return symptomDao.getSymptomById(symptomId)
    }

    suspend fun deleteAll(){
        symptomDao.deleteSymptom()
    }

}
