package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Symptom")
data class Symptom(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "symptom_name")
    @Expose
    @SerializedName("symptom_name")
    val symptom_name: String,

    @ColumnInfo(name = "symptom_image")
    @Expose
    @SerializedName("symptom_image")
    val symptom_image: String?,

    @ColumnInfo(name = "symptom_description")
    @Expose
    @SerializedName("symptom_description")
    val symptom_description: String?,
)
