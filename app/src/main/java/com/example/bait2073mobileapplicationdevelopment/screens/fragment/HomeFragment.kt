package com.example.bait2073mobileapplicationdevelopment.screens.fragment


import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentChangePasswordBinding

import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentHomeBinding
import com.example.bait2073mobileapplicationdevelopment.viewmodel.StartWorkOutViewModel
import java.text.DecimalFormat


class HomeFragment : Fragment() {

    private lateinit var dialog: Dialog

    private lateinit var binding: FragmentHomeBinding
    lateinit var viewModelStartWorkout: StartWorkOutViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        showDialogNotification()

        binding.personalizedRecommend.setOnClickListener{

            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .build()
            findNavController().navigate(R.id.action_homeFragment_to_workoutFragment, null, navOptions)
        }

        //intialize view model
        viewModelStartWorkout = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(StartWorkOutViewModel::class.java)




        val userData = retrieveUserDataFromSharedPreferences(requireContext())
        val userId = userData?.first


        viewModelStartWorkout.allStartWorkout.observe(viewLifecycleOwner) { list ->
            list?.let {


                var totalCalorie = 0.0 // Initialize totalCalorie to zero
                var durationInSeconds = 0


                for (workout in list) {
                    if (workout.userId == userId) {
                        workout.calorie?.let {
                            totalCalorie += it // Add the calorie value of each workout to totalCalorie
                        }
                        workout.duration?.let {
                            durationInSeconds += it // Add the calorie value of each workout to totalCalorie
                        }
                    }
                }

                // Calculate minutes and seconds
                val minutes = durationInSeconds / 60
                val seconds = durationInSeconds % 60

                // Format minutes and seconds into "mm:ss" format
                val formattedDuration = String.format("%02d:%02d", minutes, seconds)
                binding.timeCal.text = "$formattedDuration"

//
//                for (workout in list) {
//                    totalCalorie += workout.calorie ?: 0.0 // Add the calorie value of each workout to totalCalorie
//                }

                val format = DecimalFormat("###.0")
                if(totalCalorie==0.0){
                    binding.calorie.text = "0.0"
                }else {
                    val formattedCalorie = format.format(totalCalorie)
                    // Now, you can display the totalCalorie in your UI
                    binding.calorie.text = "$formattedCalorie"
                }
            }
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

    private fun showDialogNotification() {
        val showDialog: ImageView? = view?.findViewById(R.id.icon_add)

        // Check if the view is not null before accessing it
        if (showDialog != null) {
            // Create the Dialog here
            dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.custom_dialog_notification)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false) // Optional
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Setting the animations to dialog

            val okay: Button = dialog.findViewById(R.id.btn_okay)
            val cancel: Button = dialog.findViewById(R.id.btn_cancel)

            okay.setOnClickListener {
                Toast.makeText(requireContext(), "Okay", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            cancel.setOnClickListener {
                Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            // Use safe call (?.) to set click listener if showDialog is not null
            showDialog.setOnClickListener {
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
                dialog.show() // Showing the dialog here
            }

            okay.setBackgroundColor(requireContext().getColor(android.R.color.holo_red_light))

            val personalizedRecommendCard: CardView? = view?.findViewById(R.id.personalizedRecommend)
            personalizedRecommendCard?.setOnClickListener {
                // Replace the current fragment with the WorkoutFragment
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_workoutFragment)
            }

            val mytrainListCard: CardView? = view?.findViewById(R.id.myTrainCardView)
            mytrainListCard?.setOnClickListener{

                view?.findNavController()?.navigate(R.id.action_homeFragment_to_myTrainList)

            }
        }

    }


}
