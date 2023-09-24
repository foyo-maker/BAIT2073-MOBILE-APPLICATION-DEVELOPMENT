package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.DiseaseSymptomDao
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Symptom
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom


class DiseaseSymptomRepository  (private val diseaseSymptomDao:DiseaseSymptomDao) {

    var allDiseaseSymptoms: LiveData<List<Disease_Symptom>> = diseaseSymptomDao.getAllDiseasesSymptom()

    fun retrieve(): LiveData<List<Disease_Symptom>> {
        return diseaseSymptomDao.getAllDiseasesSymptom()
    }
    suspend fun insert(diseaseSymptom: Disease_Symptom) {
        diseaseSymptomDao.insertDiseaseSymptom(diseaseSymptom)
    }
    suspend fun getDiseaseSymptomById(diseaseSymptomId: Int): LiveData<Disease_Symptom> {
        return diseaseSymptomDao.getDiseaseSymptomById(diseaseSymptomId)
    }
    suspend fun deleteAll(){
        diseaseSymptomDao.deleteDiseaseSymptom()
    }
}