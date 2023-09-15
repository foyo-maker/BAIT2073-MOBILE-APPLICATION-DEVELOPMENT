package com.example.bait2073mobileapplicationdevelopment.screens.dialog

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.UpdateBmiUser
import com.example.bait2073mobileapplicationdevelopment.entities.UserRating
import com.example.bait2073mobileapplicationdevelopment.screens.password.ChangePassword.ChangePasswordViewModel
import com.example.bait2073mobileapplicationdevelopment.screens.profile.BMI.RequestBmiActivityViewModel

class RatingDialog(context: Context, private val viewModel: RatingDialogViewModel) : Dialog(context) {

    private var userRate = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_rating)

        val rateNowBtn: AppCompatButton = findViewById(R.id.rateNowBtn)
        val laterBtn: AppCompatButton = findViewById(R.id.laterBtn)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val ratingImage: ImageView = findViewById(R.id.ratingImage)

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->

            when {
                rating <= 1 -> ratingImage.setImageResource(R.drawable.img_rating1)
                rating <= 2 -> ratingImage.setImageResource(R.drawable.img_rating2)
                rating <= 3 -> ratingImage.setImageResource(R.drawable.img_rating3)
                rating <= 4 -> ratingImage.setImageResource(R.drawable.img_rating4)
                rating <= 5 -> ratingImage.setImageResource(R.drawable.img_rating5)
            }

            //animate emoji image
            animateImage(ratingImage)
            userRate = rating
        }

        val userData = retrieveUserDataFromSharedPreferences(context.applicationContext)
        val userId = userData?.first


        rateNowBtn.setOnClickListener {
            // Implement the logic for the "Rate Now" button click here


            val user = UserRating(
                userId,
                userRate

            )
            viewModel.updateUser(userId ?: 0, user)
            Toast.makeText(context.applicationContext, "Thanks For Rating Us", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        laterBtn.setOnClickListener {
            // Implement the logic for the "Later" button click here
            dismiss()
        }
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


    private fun animateImage(ratingImage: ImageView) {
        val scaleAnimation = ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.fillAfter = true
        scaleAnimation.duration = 200
        ratingImage.startAnimation(scaleAnimation)
    }


}
