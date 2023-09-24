package com.example.bait2073mobileapplicationdevelopment.screens.disease;

import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.DiseaseRecipeAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDiseaseRecipeBinding
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseList.DiseaseListViewModel
import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseRecipeList.DiseaseRecipeListViewModel

class DiseaseRecipeFragment: Fragment() {
    private lateinit var viewModel: DiseaseRecipeListViewModel
    private lateinit var adapter: DiseaseRecipeAdapter
    private lateinit var binding: FragmentDiseaseRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiseaseRecipeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            DiseaseRecipeListViewModel::class.java)

        val args = DiseaseRecipeFragmentArgs.fromBundle(requireArguments())
        val disease_id = args.diseaseId
        Log.i("lol is this disease id","$disease_id")

        adapter = DiseaseRecipeAdapter(requireContext())

        viewModel.getDiseaseRecipeData(disease_id)
        viewModel.getLoadDiseaseRecipeObservable().observe(viewLifecycleOwner, Observer { diseaseSymptoms ->
            Log.i("finddaolemaaaaaa","$diseaseSymptoms")
            if (diseaseSymptoms != null) {
                // Update the adapter with the fetched data
                adapter.setData(diseaseSymptoms)
                adapter.notifyDataSetChanged()
            } else {
                // Handle the case where data is not available
                Log.i("wandndn","$diseaseSymptoms")
                Toast.makeText(requireContext(), "No data found...", Toast.LENGTH_LONG).show()
            }
        })


        val recipeRecyclerView = binding.recycleRecipe
       recipeRecyclerView.setHasFixedSize(true)
        recipeRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        recipeRecyclerView.adapter = adapter

        return binding.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1000) {
            viewModel.getDiseaseRecipeList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDiseaseRecipeList()
    }

}