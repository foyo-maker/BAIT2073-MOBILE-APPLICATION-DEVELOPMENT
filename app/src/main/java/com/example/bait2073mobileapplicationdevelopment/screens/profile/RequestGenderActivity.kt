package com.example.bait2073mobileapplicationdevelopment.screens.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityRequestGenderBinding

class RequestGenderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestGenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRequestGenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maleImage.setOnClickListener {
            // Change the image resource to "gender_male_click"
            binding.maleImage.setImageResource(R.drawable.gender_male_click)

            // Change the text color to blue
            binding.maleText.setTextColor(resources.getColor(android.R.color.holo_blue_dark))

            // Reset female image and text
            binding.femaleImage.setImageResource(R.drawable.gender_female)
            binding.femaleText.setTextColor(resources.getColor(android.R.color.black))
        }

        binding.femaleImage.setOnClickListener {
            // Change the image resource to "gender_female_click"
            binding.femaleImage.setImageResource(R.drawable.gender_female_click)

            // Change the text color to pink (assuming you have defined this color in colors.xml)
            binding.femaleText.setTextColor(resources.getColor(R.color.pink))

            // Reset male image and text
            binding.maleImage.setImageResource(R.drawable.gender_male)
            binding.maleText.setTextColor(resources.getColor(android.R.color.black))
        }

        binding.nextButton.setOnClickListener {
            // Start the ActivityRequestBmiActivity when the "next_button" is clicked
            val intent = Intent(this, RequestBmiActivity::class.java)
            startActivity(intent)
        }
    }
}
