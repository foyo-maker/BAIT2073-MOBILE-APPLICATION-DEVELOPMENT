package com.example.bait2073mobileapplicationdevelopment.screens.admin.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bait2073mobileapplicationdevelopment.adapter.RecipeListAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentRecipeListBinding
import com.example.bait2073mobileapplicationdevelopment.screens.admin.recipeList.RecipeListViewModel

class RecipeListFragment : Fragment(){
    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var viewModel: RecipeListViewModel
    private lateinit var adapter: RecipeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
           RecipeListViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.recipeListViewModel= viewModel

        val recipeRecyclerView = binding.recycleView

        recipeRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecipeListAdapter(mutableListOf())
        recipeRecyclerView.adapter = adapter

        viewModel.recipeList.observe(viewLifecycleOwner, Observer { data ->
            data?.let{
                adapter.setData(data)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRecipeList()
    }
}