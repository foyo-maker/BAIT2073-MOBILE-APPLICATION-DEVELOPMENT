package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "email")
    @Expose
    @SerializedName("email")
    val email: String?,

    @ColumnInfo(name = "gender")
    @Expose
    @SerializedName("gender")
    val gender: String?,

    @ColumnInfo(name = "image")
    @Expose
    @SerializedName("image")
    val image: String?,

    @ColumnInfo(name = "phone")
    @Expose
    @SerializedName("phone")
    val phone: String?,

    @ColumnInfo(name = "role")
    @Expose
    @SerializedName("role")
    val role: Int,

    @ColumnInfo(name = "birthdate")
    @Expose
    @SerializedName("birthdate")
    val birthdate: String?,


)

data class UserList(val data: List<User>)

data class UserResponse(val data: User?,val code: Int?)