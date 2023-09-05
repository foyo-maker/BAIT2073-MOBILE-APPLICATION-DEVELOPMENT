package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.screens.workout.WorkoutDetailsActivity

class WorkoutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_workout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workOutCardView: CardView = view.findViewById(R.id.workOutCardView)
        // Set click listener on the CardView
        workOutCardView.setOnClickListener {
            // Start the new activity here
            val intent = Intent(requireContext(), WorkoutDetailsActivity::class.java)
            startActivity(intent)
        }
    }



}