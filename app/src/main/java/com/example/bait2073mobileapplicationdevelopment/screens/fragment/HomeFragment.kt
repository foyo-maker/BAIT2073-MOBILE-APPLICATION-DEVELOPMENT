package com.example.bait2073mobileapplicationdevelopment.screens.fragment


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Dialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentChangePasswordBinding

import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentHomeBinding
import com.example.bait2073mobileapplicationdevelopment.utilities.ExerciseNotificationClass
import com.example.bait2073mobileapplicationdevelopment.viewmodel.StartWorkOutViewModel
import java.text.DecimalFormat
import java.util.Calendar


class HomeFragment : Fragment() {

    private lateinit var dialog: Dialog
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModelStartWorkout: StartWorkOutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.personalizedRecommend.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_homeFragment_to_workoutFragment,
                null,
                navOptions
            )
        }

        binding.myTrainCardView.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.myTrainList, true)
                .build()
            findNavController().navigate(
                R.id.action_homeFragment_to_myTrainList,
                null,
                navOptions
            )
        }

//        binding.iconAdd.setOnClickListener {
//            showDialogNotification()
//        }

        viewModelStartWorkout = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(StartWorkOutViewModel::class.java)

        val userData = retrieveUserDataFromSharedPreferences(requireContext())
        val userId = userData?.first

        viewModelStartWorkout.allStartWorkout.observe(viewLifecycleOwner) { list ->
            list?.let {
                var totalCalorie = 0.0
                var durationInSeconds = 0

                for (workout in list) {
                    if (workout.userId == userId) {
                        workout.calorie?.let {
                            totalCalorie += it
                        }
                        workout.duration?.let {
                            durationInSeconds += it
                        }
                    }
                }

                val minutes = durationInSeconds / 60
                val seconds = durationInSeconds % 60
                val formattedDuration = String.format("%02d:%02d", minutes, seconds)
                binding.timeCal.text = "$formattedDuration"

                val format = DecimalFormat("###.0")
                if (totalCalorie == 0.0) {
                    binding.calorie.text = "0.0"
                } else {
                    val formattedCalorie = format.format(totalCalorie)
                    binding.calorie.text = "$formattedCalorie"
                }
            }
        }

        createNotificationChannel()

        return binding.root
    }

    private fun retrieveUserDataFromSharedPreferences(context: Context): Pair<Int, String>? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt(
            "UserId",
            -1
        )
        val userName = sharedPreferences.getString(
            "UserName",
            null
        )
        if (userId != -1 && userName != null) {
            return Pair(userId, userName)
        }
        return null
    }

    private fun showDialogNotification() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_notification)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {
            val timePicker = dialog.findViewById<TimePicker>(R.id.timePicker)
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute

            Log.e("selectedHour","$selectedHour")
            // Set the notification based on selected time
            setNotification(requireContext(), selectedHour, selectedMinute)
            dialog.dismiss()
        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show()

        okay.setBackgroundColor(requireContext().getColor(android.R.color.holo_red_light))

        val personalizedRecommendCard: CardView? = view?.findViewById(R.id.personalizedRecommend)
        personalizedRecommendCard?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_workoutFragment)
        }

        val mytrainListCard: CardView? = view?.findViewById(R.id.myTrainCardView)
        mytrainListCard?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_myTrainList)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "LemubitChannel"
            val description = "Channel for Lemubit Reminder"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel("notifyLemubit", name, importance)
            channel.description = description

            val notificationManager =
                requireContext().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setNotification(context: Context, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ExerciseNotificationClass::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val currentTime = Calendar.getInstance()
        if (calendar.before(currentTime)) {
            calendar.add(Calendar.DATE, 1)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val canScheduleExactAlarms = alarmManager.canScheduleExactAlarms()

            if (canScheduleExactAlarms) {
                // Your app has permission to schedule exact alarms
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else {
                // Your app does not have permission to schedule exact alarms, handle the case accordingly
                // You can request permission or use inexact alarms as an alternative
                // For example, you can use alarmManager.setExactAndAllowWhileIdle() with a lower frequency
                // or show a message to the user explaining why the exact alarm couldn't be set.
            }
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }
}



