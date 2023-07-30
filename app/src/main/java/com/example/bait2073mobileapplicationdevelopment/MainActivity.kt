package com.example.bait2073mobileapplicationdevelopment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bait2073mobileapplicationdevelopment.auth.LoginActivity

class MainActivity : AppCompatActivity() {

    private val SPLASH_SCREEN = 3000L // Change SPLASH_SCREEN type to Long
    private lateinit var image: ImageView
    private lateinit var logo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize your views using findViewById
        image = findViewById(R.id.logo_image)
        logo = findViewById(R.id.logo_text)

        val topAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        // Set animations to views
        image.startAnimation(topAnim)
        logo.startAnimation(bottomAnim)

        // Delay the transition to the next screen
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, LoginActivity::class.java)

            val pairs = arrayOf<Pair<View, String>>(
                Pair(image, "logo_image"),
                Pair(logo, "logo_text")
            )

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val options = android.app.ActivityOptions.makeSceneTransitionAnimation(
                    this@MainActivity,
                    *pairs
                )
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }
            finish()
        }, SPLASH_SCREEN)
    }
}
