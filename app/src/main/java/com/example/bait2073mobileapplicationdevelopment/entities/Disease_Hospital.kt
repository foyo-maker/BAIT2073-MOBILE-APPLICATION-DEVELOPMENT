package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Disease_Hospital", foreignKeys = [
    ForeignKey(entity = Disease::class, parentColumns = ["id"], childColumns = ["disease_id"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Hospital::class, parentColumns = ["id"], childColumns = ["hospital_id"], onDelete = ForeignKey.CASCADE)
])

data class Disease_Hospital (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "disease_id")
    @Expose
    @SerializedName("disease_id")
    val disease_id: Int,

    @ColumnInfo(name = "hospital_id")
    @Expose
    @SerializedName("hospital_id")
    val hospital_id: Int,
)