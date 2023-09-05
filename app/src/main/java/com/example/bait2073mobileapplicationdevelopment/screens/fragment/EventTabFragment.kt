package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.EventAdapter
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentEventTabBinding
import com.google.android.material.tabs.TabLayout


class EventTabFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var binding: FragmentEventTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_tab, container, false)
        tabLayout = binding.tabLayout
        viewPager = binding.viewpager




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout.setupWithViewPager(viewPager)

        val eventAdapter = EventAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT).apply {
            addFragment(EventFragment(), "EXPLORE")
            addFragment(OngoingFragment(), "ONGOING")
        }
        viewPager.adapter = eventAdapter
    }
}
