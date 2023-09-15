package com.example.bait2073mobileapplicationdevelopment.screens.dashboard.UserDashboard

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDashboardBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentHomeBinding
import com.example.bait2073mobileapplicationdevelopment.viewmodel.StartWorkOutViewModel
import java.text.DecimalFormat

class DashboardFragment: Fragment() {


    private lateinit var binding: FragmentDashboardBinding
    lateinit var viewModelStartWorkout: StartWorkOutViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentDashboardBinding.inflate(inflater, container, false)



        val userData = retrieveUserDataFromSharedPreferences(requireContext())
        val userName = userData?.second
        val userId = userData?.first
        binding.nameTextView.text = userName


        //intialize view model
        viewModelStartWorkout = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(StartWorkOutViewModel::class.java)



        viewModelStartWorkout.allStartWorkout.observe(viewLifecycleOwner){list->
            list?.let{


                var totalCalorie = 0.0 // Initialize totalCalorie to zero

                for (workout in list) {
                    if (workout.userId == userId) {
                        workout.calorie?.let {
                            totalCalorie += it // Add the calorie value of each workout to totalCalorie
                        }
                    }
                }

//
//                for (workout in list) {
//                    totalCalorie += workout.calorie ?: 0.0 // Add the calorie value of each workout to totalCalorie
//                }

                val format = DecimalFormat("###.0")
                val formattedCalorie = format.format(totalCalorie)
                // Now, you can display the totalCalorie in your UI
                binding.totalCalorie.text = "$formattedCalorie"
            }
        }



        return binding.root
    }






    private fun retrieveUserDataFromSharedPreferences(context: Context): Pair<Int, String>? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt(
            "UserId",
            -1
        ) // -1 is a default value if the key is not found
        val userName = sharedPreferences.getString(
            "UserName",
            null
        ) // null is a default value if the key is not found
        if (userId != -1 && userName != null) {
            return Pair(userId, userName)
        }
        return null
    }
}



