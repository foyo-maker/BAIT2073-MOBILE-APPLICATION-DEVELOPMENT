package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Disease_Recipe", foreignKeys = [
    ForeignKey(entity = Disease::class, parentColumns = ["id"], childColumns = ["disease_id"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Recipe::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)
])

data class Disease_Recipe (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "disease_id")
    @Expose
    @SerializedName("disease_id")
    val disease_id: Int,

    @ColumnInfo(name = "recipe_id")
    @Expose
    @SerializedName("recipe_id")
    val recipe_id: Int,
)