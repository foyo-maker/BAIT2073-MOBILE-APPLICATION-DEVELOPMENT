package com.example.bait2073mobileapplicationdevelopment.screens.staff.customer

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bait2073mobileapplicationdevelopment.adapter.CustomerAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentCustomerListBinding
import com.example.bait2073mobileapplicationdevelopment.entities.User
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.UserList
import com.example.bait2073mobileapplicationdevelopment.screens.fragment.CustomerListFragment

class CustomerListFragment: Fragment(), CustomerAdapter.CustomerClickListener, PopupMenu.OnMenuItemClickListener {

    lateinit var recyclerViewAdapter: CustomerAdapter
    lateinit var viewModel: CustomerViewModel
    private lateinit var binding: FragmentCustomerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCustomerListBinding.inflate(inflater, container, false)

        initRecyclerView()
        initViewModel()
        searchUser()

        binding.addUserBtn.setOnClickListener {
//            startActivity(Intent(this@MainActivity, CreateNewUserActivity::class.java))
        }

        return binding.root
    }


    private fun searchUser() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    recyclerViewAdapter.filerList(newText)
                }
                return true

            }
        })
    }
    private fun initUi() {


    }
    private fun initRecyclerView() {
        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        recyclerViewAdapter = CustomerAdapter(requireContext(), this)
        binding.recycleView.adapter = recyclerViewAdapter

    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(CustomerViewModel::class.java)


        viewModel.getUserListObserverable().observe(viewLifecycleOwner, Observer<List<User?>> {userListResponse ->
            if(userListResponse == null) {
                Toast.makeText(requireContext(), "no result found...", Toast.LENGTH_LONG).show()
            } else {
//                recyclerViewAdapter.updateList(it.toList().get(1))
                val userList = userListResponse.filterNotNull().toMutableList()
                Log.i("haha", "$userList")
                recyclerViewAdapter.updateList(userList)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUsers()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1000) {
            viewModel.getUsers()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onItemClicked(user: User) {

        val action = CustomerListFragmentDirections.actionCustomerListFragmentToUserForm(user.id?:0)
        this.findNavController().navigate(action)

    }

    override fun onLongItemClicked(user: User, cardView: CardView) {
        TODO("Not yet implemented")
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }
}