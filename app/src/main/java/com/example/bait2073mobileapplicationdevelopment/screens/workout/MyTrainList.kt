package com.example.bait2073mobileapplicationdevelopment.screens.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentHomeBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentMyTrainListBinding

class MyTrainList : Fragment() {


    private lateinit var binding: FragmentMyTrainListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyTrainListBinding.inflate(inflater, container, false)
        return binding.root

    }
}