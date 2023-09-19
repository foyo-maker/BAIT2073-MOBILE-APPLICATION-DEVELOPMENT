package com.example.bait2073mobileapplicationdevelopment.screens.admin.hospitalList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bait2073mobileapplicationdevelopment.adapter.HospitalListAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentHospitalListBinding
import com.example.bait2073mobileapplicationdevelopment.screens.admin.hospitalList.HospitalListViewModel

class HospitalListFragment : Fragment(){
    private lateinit var binding: FragmentHospitalListBinding
    private lateinit var viewModel: HospitalListViewModel
    private lateinit var adapter: HospitalListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
           HospitalListViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.hospitalListViewModel= viewModel

        val hospitalRecyclerView = binding.recycleView

        hospitalRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HospitalListAdapter(mutableListOf())
        hospitalRecyclerView.adapter = adapter

        viewModel.hospitalList.observe(viewLifecycleOwner, Observer { data ->
            data?.let{
                adapter.setData(data)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHospitalList()
    }
}