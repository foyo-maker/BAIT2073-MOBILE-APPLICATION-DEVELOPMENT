package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.DiseaseRecipeDao
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Recipe
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Symptom
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom

class DiseaseRecipeRepository (private val diseaseRecipeDao: DiseaseRecipeDao) {

    var allDiseaseRecipes: LiveData<List<Disease_Recipe>> = diseaseRecipeDao.getAllDiseasesRecipe()

    fun retrieve(): LiveData<List<Disease_Recipe>> {
        return diseaseRecipeDao.getAllDiseasesRecipe()
    }

    suspend fun insert(diseaseRecipe: Disease_Recipe) {
        diseaseRecipeDao.insertDiseaseRecipe(diseaseRecipe)
    }
    suspend fun getDiseaseRecipeById(diseaseRecipeId: Int): LiveData<Disease_Recipe> {
        return diseaseRecipeDao.getDiseaseRecipeById(diseaseRecipeId)
    }
    suspend fun deleteAll(){
        diseaseRecipeDao.deleteDiseaseRecipe()
    }


}