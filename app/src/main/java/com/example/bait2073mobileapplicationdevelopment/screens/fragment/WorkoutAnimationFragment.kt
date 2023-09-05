package com.example.bait2073mobileapplicationdevelopment.screens.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R


class WorkoutAnimationFragment : Fragment() {


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