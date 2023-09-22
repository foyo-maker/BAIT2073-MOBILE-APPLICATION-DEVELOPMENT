package com.example.bait2073mobileapplicationdevelopment.utilities

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.bait2073mobileapplicationdevelopment.R

class ExerciseNotificationClass : BroadcastReceiver() {

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent?) {


        val channelId = "notifyLemubit"
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.logo_image)
            .setContentTitle("Exercise Reminder")
            .setContentText("It's time for your exercise!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(200, builder.build())

    }
}


