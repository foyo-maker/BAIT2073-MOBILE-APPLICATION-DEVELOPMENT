package com.example.bait2073mobileapplicationdevelopment


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bait2073mobileapplicationdevelopment.screens.fragment.WorkoutAnimationFragment
import com.example.bait2073mobileapplicationdevelopment.screens.fragment.WorkoutVideoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            WorkoutVideoFragment()
        } else {
            WorkoutAnimationFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
