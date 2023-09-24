package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Recipe")
data class Recipe(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @Expose
        @SerializedName("id")
        val id: Int?,

        @ColumnInfo(name = "recipe_name")
        @Expose
        @SerializedName("recipe_name")
        val recipe_name: String,

        @ColumnInfo(name = "recipe_image")
        @Expose
        @SerializedName("recipe_image")
        val recipe_image: String?,

        @ColumnInfo(name = "recipe_description")
        @Expose
        @SerializedName("recipe_description")
        val recipe_description: String?,

        @ColumnInfo(name = "recipe_servings")
        @Expose
        @SerializedName("recipe_servings")
        val recipe_servings: Int?,
)