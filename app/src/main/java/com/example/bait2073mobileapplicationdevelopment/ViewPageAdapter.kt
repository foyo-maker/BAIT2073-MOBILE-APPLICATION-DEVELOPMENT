package com.example.bait2073mobileapplicationdevelopment

import LoginTabFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            SignupTabFragment()
        } else {
            LoginTabFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
