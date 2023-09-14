package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Workout")
data class Workout(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "description")
    @Expose
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "link")
    @Expose
    @SerializedName("link")
    val link: String?,

    @ColumnInfo(name = "gifimage")
    @Expose
    @SerializedName("gifimage")
    val gifimage: String?,

    @ColumnInfo(name = "calorie")
    @Expose
    @SerializedName("calorie")
    val calorie: Double?,

    @ColumnInfo(name = "bmi_status")
    @Expose
    @SerializedName("bmi_status")
    val bmi_status: String?,


    ): java.io.Serializable