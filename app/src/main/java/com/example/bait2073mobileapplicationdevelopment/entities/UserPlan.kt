package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_plan" , foreignKeys  = [
    ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"],  onDelete = ForeignKey.CASCADE)

])
data class UserPlan (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "user_id")
    @Expose
    @SerializedName("user_id")
    val user_id: Int,

    @ColumnInfo(name = "plan_name")
    @Expose
    @SerializedName("plan_name")
    val name: String

)