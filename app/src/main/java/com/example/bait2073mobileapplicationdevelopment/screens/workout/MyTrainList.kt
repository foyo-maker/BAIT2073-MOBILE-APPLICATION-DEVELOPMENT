package com.example.bait2073mobileapplicationdevelopment.screens.workout

import UserPlanListAdapter
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentHomeBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentMyTrainListBinding
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlan
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.viewmodel.UserPlanViewModel

class MyTrainList : Fragment(), UserPlanListAdapter.UserPlanClickListener, PopupMenu.OnMenuItemClickListener {

    lateinit var recyclerViewAdapter: UserPlanListAdapter
    lateinit var viewModel: MyTrainViewModel
    private lateinit var binding: FragmentMyTrainListBinding
    lateinit var selectedPlanList: UserPlanList
    lateinit var selectedPlan: UserPlan
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_train_list, container, false)
        binding = FragmentMyTrainListBinding.inflate(inflater, container, false)
        initViewModel()
        initRecyclerView()

        Log.e("mytrainlist","mytrain")

        binding.ActionBtn.setOnClickListener {
            val action = MyTrainListDirections.actionMyTrainListToAddPlanLIst()
            this.findNavController().navigate(action)
        }

        return binding.root

    }

    private fun retrieveUserDataFromSharedPreferences(context: Context): Pair<Int, String>? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt(
            "UserId",
            -1
        ) // -1 is a default value if the key is not found
        val userName = sharedPreferences.getString(
            "UserName",
            null
        ) // null is a default value if the key is not found
        if (userId != -1 && userName != null) {
            return Pair(userId, userName)
        }
        return null
    }

    private fun initRecyclerView() {
        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        recyclerViewAdapter = UserPlanListAdapter(requireContext(), this)
        binding.recycleView.adapter = recyclerViewAdapter
        //recyclerViewAdapter.setData() // Replace 'yourUserPlanListData' with your actual data
//        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun initViewModel() {


        val userData = retrieveUserDataFromSharedPreferences(requireContext())
        val userId = userData?.first!!
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(
            MyTrainViewModel::class.java
        )

        viewModel.getUserPlanObserverable()
            .observe(viewLifecycleOwner, Observer<List<UserPlan?>> { userListResponse ->
                if (userListResponse == null) {
                    Toast.makeText(requireContext(), "no result found...", Toast.LENGTH_LONG).show()
                } else {
//                recyclerViewAdapter.updateList(it.toList().get(1))
                    val userPlanList = userListResponse.filterNotNull().toMutableList()
                    Log.i("foyooooo", "$userPlanList")

                    recyclerViewAdapter.updateUserPlanList(userPlanList)
                    recyclerViewAdapter.setData(userPlanList)

                }
            })

        Log.e("user", "$userId")
        viewModel.getPlan(userId)
    }

    override fun onItemClicked(userPlan: UserPlan, userPlanList: UserPlanList) {
        val action = MyTrainListDirections.actionMyTrainListToAddPlanLIst()
        this.findNavController().navigate(action)
    }

    override fun OnLongItemClicked(
        userPlan: UserPlan,
        userPlanList: UserPlanList,
        cardView: CardView
    ) {
        selectedPlanList = userPlanList
        selectedPlan = userPlan
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {

        val popup = PopupMenu(requireContext(), cardView)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val userData = retrieveUserDataFromSharedPreferences(requireContext())
        val userId = userData?.first!!
        if (item?.itemId == R.id.delete_note) {

            viewModel.deleteUserPlan(userId, selectedPlan.id)
        }
        return false
    }

}
