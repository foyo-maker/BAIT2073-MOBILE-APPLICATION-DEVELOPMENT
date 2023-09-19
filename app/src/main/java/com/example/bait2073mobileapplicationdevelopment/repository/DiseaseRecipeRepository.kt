package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.DiseaseRecipeDao
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Recipe

class DiseaseRecipeRepository (private val diseaseRecipeDao: DiseaseRecipeDao) {

    var allDiseaseRecipes: LiveData<List<Disease_Recipe>> = diseaseRecipeDao.getAllDiseasesRecipe()

    suspend fun insert(diseaseRecipe: Disease_Recipe) {
        diseaseRecipeDao.insertDiseaseRecipe(diseaseRecipe)
    }
}