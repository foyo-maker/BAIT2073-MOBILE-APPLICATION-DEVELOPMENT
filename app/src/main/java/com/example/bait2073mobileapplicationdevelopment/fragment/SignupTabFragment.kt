package com.example.bait2073mobileapplicationdevelopment.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.profile.RequestGenderActivity
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentSignupTabBinding



class SignupTabFragment : Fragment() {
    private lateinit var binding: FragmentSignupTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = FragmentSignupTabBinding.inflate(layoutInflater)

        // Set click listener for the signup button
        binding.signupButton.setOnClickListener {
            // Navigate to the RequestGenderActivity

            Log.d("gg", "item not clciked")
            val intent = Intent(requireContext(), RequestGenderActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}
