package com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseForm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDiseaseChoicesBinding
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Symptom

import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseForm.DiseaseChoicesFragmentDirections

class DiseaseChoicesFragment: Fragment(){
    private lateinit var binding: FragmentDiseaseChoicesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiseaseChoicesBinding.inflate(inflater, container, false)

        binding.addDiseaseBtn.setOnClickListener {
            val action =
                DiseaseChoicesFragmentDirections.actionDiseaseChoicesFragmentToCreateDiseaseFragment(0)
            this.findNavController().navigate(action)
        }

        binding.addDiseaseSymptomBtn.setOnClickListener {
            val action =DiseaseChoicesFragmentDirections.actionDiseaseChoicesFragmentToDiseaseSymptomListFragment()

            this.findNavController().navigate(action)
        }

        binding.addDiseaseRecipeBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Navigating to disease recipe management...",
                Toast.LENGTH_SHORT
            ).show()
            val action =
                DiseaseChoicesFragmentDirections.actionDiseaseChoicesFragmentToDiseaseRecipeListFragment()
            this.findNavController().navigate(action)  //need configure and change to go diseaserecipelist
        }

        binding.addDiseaseHospitalBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Navigating to disease hospital management...",
                Toast.LENGTH_SHORT
            ).show()
            val action =
                DiseaseChoicesFragmentDirections.actionDiseaseChoicesFragmentToDiseaseHospitalListFragment()
            this.findNavController().navigate(action)  //need configure and change to go diseasehospitallist
        }

        return binding.root
    }
}