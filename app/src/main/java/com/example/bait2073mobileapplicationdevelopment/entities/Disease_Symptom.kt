package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Disease_Symptom", foreignKeys = [
    ForeignKey(entity = Disease::class, parentColumns = ["id"], childColumns = ["disease_id"], onDelete = CASCADE),
    ForeignKey(entity = Symptom::class, parentColumns = ["id"], childColumns = ["symptom_id"], onDelete = CASCADE)
])

data class Disease_Symptom (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "disease_id")
    @Expose
    @SerializedName("disease_id")
    val disease_id: Int,

    @ColumnInfo(name = "symptom_id")
    @Expose
    @SerializedName("symptom_id")
    val symptom_id: Int,
)