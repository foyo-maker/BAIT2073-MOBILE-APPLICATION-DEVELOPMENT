package com.example.bait2073mobileapplicationdevelopment.screens.workout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.PersonalizedWorkOutAdapter
import com.example.bait2073mobileapplicationdevelopment.adapter.userPlanWorkoutShowAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentDisplayPersonalizedWorkoutBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentUserPlanWorkoutShowBinding
import com.example.bait2073mobileapplicationdevelopment.entities.PersonalizedWorkout
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.example.bait2073mobileapplicationdevelopment.screens.personalized.WorkoutFragmentDirections
import com.example.bait2073mobileapplicationdevelopment.screens.personalized.WorkoutFragmentViewModel
import com.example.bait2073mobileapplicationdevelopment.viewmodel.PersonalizedWorkoutViewModel


class userPlanWorkoutShow : Fragment(), userPlanWorkoutShowAdapter.WorkoutClickListener {

    lateinit var recyclerViewAdapter: userPlanWorkoutShowAdapter
    lateinit var viewModel: userrPlanWorkoutViewModel
//    lateinit var viewModelRoom: PersonalizedWorkoutViewModel
    private lateinit var binding: FragmentUserPlanWorkoutShowBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("userPlanWork","userPlanWork")
        binding = FragmentUserPlanWorkoutShowBinding.inflate(inflater, container, false)

//        viewModelRoom = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
//        ).get(PersonalizedWorkoutViewModel::class.java)
        initViewModel()

        initRecyclerView()
        searchWorkout()

//        binding.startButton.setOnClickListener {
//
//            findNavController().navigate(R.id.)
//
//        }

        return binding.root
    }


    private fun searchWorkout() {
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
        recyclerViewAdapter = userPlanWorkoutShowAdapter(requireContext(), this)
        binding.recycleView.adapter = recyclerViewAdapter

    }

    fun initViewModel() {
        val args = userPlanWorkoutShowArgs.fromBundle(requireArguments())
        val user_plan_id = args.userPlanId
        Log.e("TestUserPlanID","$user_plan_id")
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(
            userrPlanWorkoutViewModel::class.java
        )


        viewModel.getWorkoutListObserverable()
            .observe(viewLifecycleOwner, Observer<List<UserPlanList?>> { userPlanListResponse ->
                if (userPlanListResponse == null) {
                    Toast.makeText(requireContext(), "no result found...", Toast.LENGTH_LONG).show()
                } else {
//                recyclerViewAdapter.updateList(it.toList().get(1))
                    val workoutList = userPlanListResponse.filterNotNull().toMutableList()
                    Log.i("workoutlist", "$workoutList")
                    recyclerViewAdapter.updateList(workoutList)
                    recyclerViewAdapter.notifyDataSetChanged()

//                    clearPersonalizedDb()
//                    insertDataIntoRoomDb(workoutList)
                }
            })


        viewModel.getPersonalizedWorkoutList(user_plan_id)
    }


//    fun insertDataIntoRoomDb(workouts: List<Workout>) {
//
//
//        try {
//            for (workout in workouts) {
//                Log.d("InsertDataIntoRoomDb", "Inserting workout with ID: ${workout}")
//                viewModelRoom.insertWorkout(
//                    PersonalizedWorkout(
//                        id = workout.id,
//                        name = workout.name,
//                        description = workout.description,
//                        link = workout.link,
//                        gifimage = workout.gifimage,
//                        calorie = workout.calorie,
//                        bmi_status = workout.bmi_status
//                    )
//                )
//            }
//        } catch (e: Exception) {
//            Log.e(
//                "InsertDataIntoRoomDb",
//                "Error inserting data into Room Database: ${e.message}",
//
//                )
//        }
//
//
//    }

//    fun clearPersonalizedDb() {
//
//
//        viewModelRoom.clearWorkout()
//
//    }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1000) {
            viewModel.getWorkoutListObserverable()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onItemClicked(userPlanList: UserPlanList) {
        // Start the new activity here
//        val intent = Intent(requireContext(), userPlanWorkoutShow::class.java)
//        intent.putExtra("userPlanList", userPlanList.id)
//        startActivity(intent)

    }


}