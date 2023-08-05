package com.example.bait2073mobileapplicationdevelopment.fragment

import HomeFragment
import android.app.Dialog
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
import android.view.ViewGroup


import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityMainBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentMainBinding
import com.example.bait2073mobileapplicationdevelopment.dialog.RatingDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: FragmentMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setTitleTextColor(Color.WHITE)

        val boldTitle = SpannableString("Healthy Life+")
        boldTitle.setSpan(StyleSpan(Typeface.BOLD), 0, boldTitle.length, 0)
        toolbar.title = boldTitle

        setSupportActionBar(toolbar)

//        setToolbarTitle("Healthy Life+", isBold = true)

        val toggle = androidx.appcompat.app.ActionBarDrawerToggle(
            this, binding.drawerLayout, toolbar,
            R.string.open_nav, R.string.close_nav
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        binding.bottomNavigationView.background = null


        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.shorts -> replaceFragment(AboutFragment())
                R.id.subscriptions ->  replaceFragment(EventTabFragment())
                R.id.report -> replaceFragment(ReportFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        replaceFragment(HomeFragment())



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


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, HomeFragment()).commit()


            }
            R.id.nav_profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, ProfileFragment()).commit()

            }
            R.id.nav_change_password-> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, ChangePasswordFragment()).commit()

            }
            R.id.nav_about -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, AboutFragment()).commit()

            }
            R.id.nav_logout -> {

                val ratingDialog = RatingDialog(this)
                ratingDialog.setContentView(R.layout.custom_dialog_rating)
                ratingDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                ratingDialog.setCancelable(false) // Optional
                ratingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

                ratingDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Setting the animations to dialog
                ratingDialog.show()
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_myDashboard -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, DashboardFragment()).commit()

            }
            R.id.nav_customer -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, CustomerListFragment()).commit()

            }



        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_layout, fragment)
        fragmentTransaction.commit()
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
