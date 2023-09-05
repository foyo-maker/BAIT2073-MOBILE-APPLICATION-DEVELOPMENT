package com.example.bait2073mobileapplicationdevelopment.screens.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatButton
import com.example.bait2073mobileapplicationdevelopment.R

class RatingDialog(context: Context) : Dialog(context) {

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
        rateNowBtn.setOnClickListener {
            // Implement the logic for the "Rate Now" button click here
            Toast.makeText(context.applicationContext, "Okay", Toast.LENGTH_SHORT).show()
            Log.i("gg", "item not clciked")
            dismiss()
        }

        laterBtn.setOnClickListener {
            // Implement the logic for the "Later" button click here
            dismiss()
        }
    }

    private fun animateImage(ratingImage: ImageView) {
        val scaleAnimation = ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.fillAfter = true
        scaleAnimation.duration = 200
        ratingImage.startAnimation(scaleAnimation)
    }
}
