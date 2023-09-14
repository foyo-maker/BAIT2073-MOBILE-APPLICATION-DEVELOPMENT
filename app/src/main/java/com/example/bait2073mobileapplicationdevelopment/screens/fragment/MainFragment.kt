package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R
import com.google.android.material.navigation.NavigationView
import android.widget.Toast
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout


import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityMainBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentMainBinding
import com.example.bait2073mobileapplicationdevelopment.screens.admin.UserForm.UserFormFragmentDirections
import com.example.bait2073mobileapplicationdevelopment.screens.auth.Login.LoginActivity
import com.example.bait2073mobileapplicationdevelopment.screens.dialog.RatingDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : AppCompatActivity(){

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.reportFragment,R.id.eventFragment,R.id.diseasePreventionFragment,R.id.dashboardFragment),
            binding.drawerLayout
        )


// Hide or show the ActionBar back button based on the current destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in setOf(R.id.homeFragment, R.id.reportFragment,R.id.eventFragment,R.id.diseasePreventionFragment,R.id.dashboardFragment)) {
                binding.bottomNav.visibility = View.VISIBLE
            } else {
                binding.bottomNav.visibility = View.INVISIBLE
//                binding.bottomNav.menu.setGroupEnabled(R.id.bottom_nav, false) // Disable menu items
//                binding.bottomNav.setOnNavigationItemSelectedListener { false } // Prevent item clicks
//                binding.bottomNav.animate().translationY(binding.bottomNav.getHeight()).setDuration(1000);
//                val layoutParams = binding.bottomNav.layoutParams as CoordinatorLayout.LayoutParams
//                layoutParams.height = 0
//                layoutParams.width = 0
//                binding.bottomNav.layoutParams = layoutParams
            }
        }





        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
//        toolbar.setTitleTextColor(Color.WHITE)
//
//        val boldTitle = SpannableString("Healthy Life+")
//        boldTitle.setSpan(StyleSpan(Typeface.BOLD), 0, boldTitle.length, 0)
//        toolbar.title = boldTitle
//
        setSupportActionBar(toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)


        binding.bottomNav.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)

//        setToolbarTitle("Healthy Life+", isBold = true)

//        val toggle = androidx.appcompat.app.ActionBarDrawerToggle(
//            this, binding.drawerLayout, toolbar,
//            R.string.open_nav, R.string.close_nav
//        )
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        navigationView.setNavigationItemSelectedListener(this)





//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().replace(
//                R.id.frame_layout,
//                HomeFragment()
//            ).commit()
//            navigationView.setCheckedItem(R.id.nav_home)
//        }




//        fab.setOnClickListener {
//            showBottomDialog()
//        }

    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (item.itemId == androidx.navigation.ui.R.id.termsAndConditions) {
//            val action = NavGraphDirections.actionGlobalTermsFragment()
//            navController.navigate(action)
//            true
//        } else {
//            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.rating -> {
                showRatingDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private fun showRatingDialog() {
        val ratingDialog = RatingDialog(this)
        ratingDialog.setContentView(R.layout.custom_dialog_rating)
        ratingDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        ratingDialog.setCancelable(false)
        ratingDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        ratingDialog.show()
    }




//    private fun showBottomDialog() {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.bottomsheetlayout)
//
//        val videoLayout = dialog.findViewById<LinearLayout>(R.id.layoutVideo)
//        val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layoutShorts)
//        val liveLayout = dialog.findViewById<LinearLayout>(R.id.layoutLive)
//        val cancelButton = dialog.findViewById<ImageView>(R.id.cancelButton)
//
//        videoLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this, "Upload a Video is clicked", Toast.LENGTH_SHORT).show()
//        }
//
//        shortsLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this, "Create a short is Clicked", Toast.LENGTH_SHORT).show()
//        }
//
//        liveLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this, "Go live is Clicked", Toast.LENGTH_SHORT).show()
//        }
//
//        cancelButton.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.show()
//        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
//        dialog.window?.setGravity(Gravity.BOTTOM)
//    }

}
