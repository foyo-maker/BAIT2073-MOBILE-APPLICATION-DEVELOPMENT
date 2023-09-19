package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Disease")
data class Disease(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "disease_name")
    @Expose
    @SerializedName("disease_name")
    val disease_name: String,

    @ColumnInfo(name = "disease_image")
    @Expose
    @SerializedName("disease_image")
    val disease_image: String?,

    @ColumnInfo(name = "disease_causes")
    @Expose
    @SerializedName("disease_causes")
    val disease_causes: String?,

    @ColumnInfo(name = "disease_description")
    @Expose
    @SerializedName("disease_description")
    val disease_description: String?,

)
