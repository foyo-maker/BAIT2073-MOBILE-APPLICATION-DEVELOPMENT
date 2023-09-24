package com.example.bait2073mobileapplicationdevelopment.screens.disease;

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.DiseaseCauseSymptomAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDiseaseCausesBinding
import com.example.bait2073mobileapplicationdevelopment.entities.Disease
import com.example.bait2073mobileapplicationdevelopment.entities.Disease_Symptom
import com.example.bait2073mobileapplicationdevelopment.entities.Symptom
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetDiseaseDataService
import com.example.bait2073mobileapplicationdevelopment.retrofitclient.RetrofitClientInstance
import com.example.bait2073mobileapplicationdevelopment.screens.disease.DiseaseCausesFragmentDirections
import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseList.DiseaseListViewModel
import com.example.bait2073mobileapplicationdevelopment.screens.disease.diseaseSymptomList.DiseaseSymptomListViewModel

class DiseaseCausesFragment : Fragment() {
    private lateinit var viewModel: DiseaseSymptomListViewModel
    private lateinit var adapter: DiseaseCauseSymptomAdapter
    private lateinit var binding: FragmentDiseaseCausesBinding
    private lateinit var diseaseViewModel : DiseaseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiseaseCausesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            DiseaseSymptomListViewModel::class.java)
        diseaseViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            DiseaseListViewModel::class.java)

        val args = DiseaseCausesFragmentArgs.fromBundle(requireArguments())
        val disease_id = args.diseaseId
        Log.i("lol is this disease id","$disease_id")

        diseaseViewModel.getSpecificDiseaseObservable().observe(viewLifecycleOwner, Observer<Disease?> { diseaseResponse ->
            if (diseaseResponse == null) {
                Toast.makeText(requireContext(), "no result found...", Toast.LENGTH_LONG).show()
            } else {
                val diseaseCauses = diseaseResponse.disease_causes
                Log.i("hahaaxxxx", "$diseaseCauses")

                binding.tvCauses.text = diseaseCauses

                adapter.notifyDataSetChanged()
            }
        })

        diseaseViewModel.getSpecificDisease(disease_id)
        adapter = DiseaseCauseSymptomAdapter(requireContext())

        viewModel.getDiseaseSymptomData(disease_id)
        viewModel.getLoadDiseaseSymptomObservable().observe(viewLifecycleOwner, Observer { diseaseSymptoms ->
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


        val causeSymptomRecyclerView = binding.recycleView
        causeSymptomRecyclerView.setHasFixedSize(true)
        causeSymptomRecyclerView.layoutManager = LinearLayoutManager(context)
        causeSymptomRecyclerView.adapter = adapter

        return binding.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1000) {
            viewModel.getDiseaseSymptomList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDiseaseSymptomList()
    }

}