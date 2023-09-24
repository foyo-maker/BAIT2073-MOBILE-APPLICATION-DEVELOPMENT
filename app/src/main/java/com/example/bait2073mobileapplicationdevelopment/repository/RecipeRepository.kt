package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.RecipeDao
import com.example.bait2073mobileapplicationdevelopment.entities.Recipe
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom

class RecipeRepository (private val recipeDao: RecipeDao) {

    var allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipe()

    fun retrieve(): LiveData<List<Recipe>> {
        return recipeDao.getAllRecipe()
    }

    suspend fun insert(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }
    suspend fun getRecipeById(recipeId: Int): LiveData<Recipe> {
        return recipeDao.getRecipeById(recipeId)
    }

    suspend fun deleteAll(){
       recipeDao.deleteRecipe()
    }
}