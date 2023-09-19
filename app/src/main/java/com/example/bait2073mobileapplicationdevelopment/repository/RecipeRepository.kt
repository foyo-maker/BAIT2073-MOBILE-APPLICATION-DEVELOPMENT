package com.example.bait2073mobileapplicationdevelopment.repository

import androidx.lifecycle.LiveData
import com.example.bait2073mobileapplicationdevelopment.dao.RecipeDao
import com.example.bait2073mobileapplicationdevelopment.entities.Recipe

class RecipeRepository (private val recipeDao: RecipeDao) {

    var allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipe()

    suspend fun insert(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }
}