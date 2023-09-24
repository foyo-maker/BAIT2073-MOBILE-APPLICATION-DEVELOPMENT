package com.example.bait2073mobileapplicationdevelopment.screens.disease;

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDiseaseDetailsBinding
import com.example.bait2073mobileapplicationdevelopment.screens.disease.DiseaseDetailFragmentDirections
import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseForm.DiseaseFormFragmentArgs
import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseList.DiseaseListViewModel
import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseSymptomList.DiseaseSymptomListViewModel


class DiseaseDetailFragment:Fragment(){
    private lateinit var binding: FragmentDiseaseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiseaseDetailsBinding.inflate(inflater, container, false)
        val args = DiseaseDetailFragmentArgs.fromBundle(requireArguments())
        val disease_id = args.diseaseId
        val disease_name = args.diseaseName
        val resultString = "What would you like to know about $disease_name ?"

        binding.diseaseDetailTitle.text = resultString

        binding.symptomButton.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Navigating to causes and symptom info...",
                Toast.LENGTH_SHORT
            ).show()
            val action =
                DiseaseDetailFragmentDirections.actionDiseaseDetailFragmentToDiseaseCausesFragment(disease_id,disease_name)
            this.findNavController().navigate(action)
        }

        binding.recipeButton.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Navigating to recipe info...",
                Toast.LENGTH_SHORT
            ).show()
            val action = DiseaseDetailFragmentDirections.actionDiseaseDetailFragmentToDiseaseRecipeFragment(disease_id,disease_name)

            this.findNavController().navigate(action)
        }

        binding.hospitalButton.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Navigating to hospital info...",
                Toast.LENGTH_SHORT
            ).show()
            val action =
                DiseaseDetailFragmentDirections.actionDiseaseDetailFragmentToDiseaseHospitalFragment(disease_id,disease_name)
            this.findNavController().navigate(action)
        }

        return binding.root
    }
}