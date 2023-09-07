package com.example.bait2073mobileapplicationdevelopment.screens.staff.customer

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    lateinit var selectedCustomer : User
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        initViewModel()
        initRecyclerView()

        observeUserDeletion()
        searchUser()

        binding.addUserBtn.setOnClickListener {

            val action = CustomerListFragmentDirections.actionCustomerListFragmentToCreateCustomerFragement(0)
            this.findNavController().navigate(action)
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

        val action = CustomerListFragmentDirections.actionCustomerListFragmentToCreateCustomerFragement(user.id?:0)
        this.findNavController().navigate(action)

    }

    override fun onLongItemClicked(user: User, cardView: CardView) {
        selectedCustomer = user
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {

        val popup = PopupMenu(requireContext(),cardView)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()

    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.delete_note){

            viewModel.deleteUser(selectedCustomer)
        }
        return false
    }

    private fun observeUserDeletion() {
        viewModel.getDeleteUserObservable().observe(viewLifecycleOwner, Observer<User?> { deletedUser ->
            if (deletedUser == null) {
                Toast.makeText(requireContext(), "Cannot Delete User", Toast.LENGTH_SHORT).show()
            } else {
                showSuccessDialog()
                viewModel.getUsers()

                // You can perform any other actions needed after successful deletion here
            }
        })
    }



    private fun showSuccessDialog(){
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_success)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false) // Optional
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Setting the animations to dialog

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {

            dialog.dismiss()
            CreateCustomerFragmentDirections.actionCreateCustomerFragementToCustomerListFragment()
        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show() // Showing the dialog here


    }


}