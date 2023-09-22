package com.example.bait2073mobileapplicationdevelopment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "EventParticipants")
class EventParticipants (
    @ColumnInfo(name="event_id")
    @Expose
    @SerializedName("event_id")
    val event_id: Int?,

    @ColumnInfo(name="user_id")
    @Expose
    @SerializedName("user_id")
    val user_id: Int?,

    @ColumnInfo(name="status")
    @Expose
    @SerializedName("status")
    val status: String,
    )