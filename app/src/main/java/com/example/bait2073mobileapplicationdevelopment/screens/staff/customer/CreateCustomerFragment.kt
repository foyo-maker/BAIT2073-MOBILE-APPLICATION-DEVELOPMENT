package com.example.bait2073mobileapplicationdevelopment.screens.staff.customer


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentCustomerFormBinding
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserResponse


class CreateCustomerFragment : Fragment() {


    private lateinit var viewModel: CreateCustomerViewModel
    private lateinit var binding: FragmentCustomerFormBinding

    var recyclerListData: MutableLiveData<List<User?>> = MutableLiveData()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {







        //val user_id = intent.getStringExtra("user_id")
        binding = FragmentCustomerFormBinding.inflate(inflater, container, false)

        initViewModel()
        createUserObservable()
        val args = CreateCustomerFragmentArgs.fromBundle(requireArguments())
        val customer_id = args.customerId

        if(customer_id != null) {
            loadUserData(customer_id)
        }
        Log.e("customerid", "$customer_id")


        binding.createButton.setOnClickListener{
            createUser(customer_id)
        }


        return binding.root
    }

    private fun loadUserData(user_id: Int?) {
        viewModel.getLoadUserObservable().observe(viewLifecycleOwner, Observer <UserResponse?>{
            if(it != null) {

                binding.eTextUserName.setText(it.data?.name)
                binding.eTextEmail.setText(it.data?.email)
                binding.createButton.setText("Update")

               binding.eTextWeight.setText(it.data?.weight.toString())
                binding.eTextHeight.setText(it.data?.height.toString())

            }
        })
        viewModel.getUserData(user_id)
    }

    private fun createUser(user_id: Int?){
        val user = User(null, binding.eTextUserName.text.toString(), binding.eTextEmail.text.toString(),"","","","",0.0,0.0)

        if(user_id == null)
            viewModel.createUser(user)
        else
            viewModel.updateUser(user_id, user)

    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(CreateCustomerViewModel::class.java)

    }

    private fun createUserObservable() {
        viewModel.getCreateNewUserObservable().observe(viewLifecycleOwner, Observer <UserResponse?>{
            if(it == null) {
                Toast.makeText(requireContext(), "Failed to create/update new user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Successfully created/updated user...", Toast.LENGTH_LONG).show()
                val action = CreateCustomerFragmentDirections.actionUserFormFragmentToCustomerListFragment()
                this.findNavController().navigate(action)
            }
        })
    }
}
