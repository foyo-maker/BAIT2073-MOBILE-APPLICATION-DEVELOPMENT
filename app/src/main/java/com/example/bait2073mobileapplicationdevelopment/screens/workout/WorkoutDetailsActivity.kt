package com.example.bait2073mobileapplicationdevelopment.screens.workout

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.ViewPagerAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.AcitivityWorkoutDetailsBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityLoginBinding
import com.example.bait2073mobileapplicationdevelopment.entities.AutenticateEmailUser
import com.example.bait2073mobileapplicationdevelopment.entities.Workout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WorkoutDetailsActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var binding:AcitivityWorkoutDetailsBinding

    private lateinit var workout: Workout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivityWorkoutDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val webView: WebView = binding.webView
        try{
            workout = intent.getSerializableExtra("workout") as Workout
        }catch(e :Exception){
            e.printStackTrace()
        }

        binding.nameTv.text = workout.name
        binding.descriptionTv.text = workout.description
        val link = workout.link
        val video = "<iframe width=\"100%\" height=\"100%\" src=\"$link\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"

        webView.loadData(video, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        val closeImg: ImageView = findViewById(R.id.close_img)
        closeImg.setOnClickListener {
            onBackPressed()
        }
    }
}