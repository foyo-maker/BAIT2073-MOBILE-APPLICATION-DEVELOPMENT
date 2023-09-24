package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

@Entity(tableName = "User_Plan_List")

data class UserPlanList(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @ColumnInfo(name = "user_plan_id")
    @Expose
    @SerializedName("user_plan_id")
    val userPlanId: Int,

    @ColumnInfo(name = "workout_id")
    @Expose
    @SerializedName("workout_id")
    val workoutId: Int,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "user_id")
    @Expose
    @SerializedName("user_id")
    val userId: Int,

    @ColumnInfo(name = "description")
    @Expose
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "gifimage")
    @Expose
    @SerializedName("gifimage")
    val gifimage: String?,

    @ColumnInfo(name = "calorie")
    @Expose
    @SerializedName("calorie")
    val calorie: Double?,

    @ColumnInfo(name = "link")
    @Expose
    @SerializedName("link")
    val link: String?,

    @ColumnInfo(name = "bmi_status")
    @Expose
    @SerializedName("bmi_status")
    val bmi_status: String?



)