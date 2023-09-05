package com.example.bait2073mobileapplicationdevelopment.screens.staff.customer

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bait2073mobileapplicationdevelopment.adapter.CustomerAdapter
import com.example.bait2073mobileapplicationdevelopment.database.HealthyLifeDatabase
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentChangePasswordBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentCustomerListBinding
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.viewmodel.UserViewModel

class CustomerFragment : Fragment(), CustomerAdapter.CustomerClickListener, PopupMenu.OnMenuItemClickListener {


    private lateinit var dialog: Dialog
    private lateinit var binding: FragmentCustomerListBinding
    private lateinit var database:HealthyLifeDatabase
    lateinit var viewModel: UserViewModel
    lateinit var adapter: CustomerAdapter
    lateinit var selectedCustomer: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentCustomerListBinding.inflate(inflater, container, false)

        initUi()
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(UserViewModel::class.java)

        viewModel.allusers.observe(viewLifecycleOwner){list->
            list?.let{
                adapter.updateList(list)
            }
        }

        database = HealthyLifeDatabase.getDatabase(requireContext())

        return binding.root
    }

    private fun initUi() {
        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        adapter = CustomerAdapter(requireContext(), this)
        binding.recycleView.adapter = adapter

        //register for activity
//        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//                result -> if(result.resultCode == Activity.RESULT_OK ){
//            val note = result.data?.getSerializableExtra("note") as? Note
//            if(note != null ){
//                viewModel.insertNote(note)
//            }
//
//        }
//        }
        binding.addUserBtn.setOnClickListener {


            //navigate user to add
//            val intent = Intent(this,AddNote::class.java)
//            getContent.launch(intent)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    adapter.filerList(newText)
                }
                return true;
            }
        })
    }

    override fun onItemClicked(user: User) {
        TODO("Not yet implemented")
    }

    override fun onLongItemClicked(user: User, cardView: CardView) {
        TODO("Not yet implemented")
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }
}