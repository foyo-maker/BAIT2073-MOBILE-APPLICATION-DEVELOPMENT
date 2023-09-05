package com.example.bait2073mobileapplicationdevelopment.screens.profile


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bait2073mobileapplicationdevelopment.databinding.AcitvityRequestBmiBinding
import com.example.bait2073mobileapplicationdevelopment.screens.fragment.MainFragment


class RequestBmiActivity : AppCompatActivity(){

    private lateinit var binding: AcitvityRequestBmiBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = AcitvityRequestBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        // Set click listener for the back arrow ImageView
        binding.imgBackArrow.setOnClickListener {
           onBackPressed()
        }
        binding.nextButton.setOnClickListener {
            val intent = Intent(this, MainFragment::class.java)
            startActivity(intent)
        }



        binding.weightPicker.setOnValueChangedListener{ _,_,_ ->
            calculateBMI()
        }

        binding.heightPicker.setOnValueChangedListener{ _,_,_ ->
            calculateBMI()
        }

    }

    private fun calculateBMI()
    {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble() / 100

        val weight = binding.weightPicker.value

        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)

//        binding.resultsTV.text = String.format("Your BMI is: %.2f", bmi)
//        binding.healthyTV.text = String.format("Considered: %s", healthyMessage(bmi))

    }

    private fun healthyMessage(bmi: Double): String
    {
        if (bmi < 18.5)
            return "Underweight"
        if(bmi < 25.0)
            return "Healthy"
        if (bmi < 30.0)
            return "Overweight"

        return "Obese"
    }

}