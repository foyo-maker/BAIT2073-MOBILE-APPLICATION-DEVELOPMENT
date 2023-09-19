package com.example.bait2073mobileapplicationdevelopment.screens.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDisplayPersonalizedWorkoutBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentMyTrainListBinding

class MyTrainListFragment : Fragment() {

    private lateinit var binding: FragmentMyTrainListBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_train_list, container, false)
        binding = FragmentMyTrainListBinding.inflate(inflater, container, false)

        return binding.root

    }



}