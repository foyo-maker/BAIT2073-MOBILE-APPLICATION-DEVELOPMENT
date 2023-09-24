package com.example.bait2073mobileapplicationdevelopment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bait2073mobileapplicationdevelopment.entities.Recipe
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom


@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)
//
//    @Update
//    fun updateRecipe(recipe: Recipe)

    @Query("SELECT * from recipe WHERE id = :recipeId")
    fun getRecipeById(recipeId: Int): LiveData<Recipe>

    @Query("SELECT * FROM recipe ORDER BY recipe_name ASC")
    fun getAllRecipe(): LiveData<List<Recipe>>

    @Query("DELETE FROM recipe")
    fun deleteRecipe()
}