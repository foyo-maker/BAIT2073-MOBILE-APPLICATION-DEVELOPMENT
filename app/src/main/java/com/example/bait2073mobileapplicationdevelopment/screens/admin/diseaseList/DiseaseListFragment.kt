package com.example.bait2073mobileapplicationdevelopment.screens.admin.diseaseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bait2073mobileapplicationdevelopment.adapter.DiseaseListAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDiseaseListBinding
import com.example.bait2073mobileapplicationdevelopment.screens.admin.symptomList.SymptomListViewModel


class DiseaseListFragment : Fragment() {
    private lateinit var binding: FragmentDiseaseListBinding
    private lateinit var viewModel: DiseaseListViewModel
    private lateinit var adapter: DiseaseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiseaseListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            DiseaseListViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.diseaseListViewModel= viewModel

        val diseaseRecyclerView = binding.recycleView

        diseaseRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DiseaseListAdapter(mutableListOf())
        diseaseRecyclerView.adapter = adapter

        viewModel.diseaseList.observe(viewLifecycleOwner, Observer { data ->
            data?.let{
                adapter.setData(data)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDiseaseList()
    }
}