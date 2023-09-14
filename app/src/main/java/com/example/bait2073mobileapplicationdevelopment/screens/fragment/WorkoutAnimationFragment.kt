package com.example.bait2073mobileapplicationdevelopment.screens.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.AutenticateEmailUser
import com.example.bait2073mobileapplicationdevelopment.entities.Workout


class WorkoutAnimationFragment : Fragment() {

    private lateinit var workout: Workout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_animation_tab, container, false)



        // Set click listener for the login button
//        loginButton.setOnClickListener {
////            validateLoginFields()
//            val intent = Intent(requireContext(), RequestGenderActivity::class.java)
//            startActivity(intent)
//        }

        return rootView
    }

    //testing

}