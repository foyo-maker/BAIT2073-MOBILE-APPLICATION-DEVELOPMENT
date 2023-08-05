package com.example.bait2073mobileapplicationdevelopment.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.Data.EventDataClass
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.AdapterEventClass
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentEventBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.RecycleviewEventBinding


class EventFragment : Fragment() {
    private lateinit var binding: FragmentEventBinding
    private lateinit var bindingRecycle: RecycleviewEventBinding

    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var detailList: Array<String>
    lateinit var recycleView: RecyclerView
    lateinit var dataList: ArrayList<EventDataClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        bindingRecycle = DataBindingUtil.inflate(inflater, R.layout.recycleview_event, container, false)

//        bindingRecycle.CardView.setOnClickListener{
//            Toast.makeText(requireContext(), "CardView Clicked!", Toast.LENGTH_SHORT).show()
////            findNavController().navigate(R.id.)
//
////            childFragmentManager.beginTransaction().replace(R.id.eventFrame, EventDetailsFragment()).commit()
//        }



//        bindingRecycle.CardView.setOnClickListener {
//            Toast.makeText(requireContext(), "CardView Clicked!", Toast.LENGTH_SHORT).show()
//            val intent = Intent(requireContext(), EventDetailsActivity::class.java)
//            startActivity(intent)
//        }



        val context: Context =  requireContext()
        val message = "Your toast message here"
        val duration = Toast.LENGTH_SHORT
//        Toast.makeText(context, message, duration).show()
        imageList = arrayOf(
            R.drawable.event_run3,
            R.drawable.event_vaccine,
            R.drawable.event_run2
        )

        titleList = arrayOf(
            "Event run until you cry in Kuala Lumpur",
            "Event free vaccine in Kuala Lumpur",
            "Event Marathon 30km in Kuala Lumpur"
        )
        detailList = arrayOf(
            "45 people going",
            "145 people going",
            "95 people going"
        )

        recycleView = binding.recycleView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.setHasFixedSize(true)

        dataList = arrayListOf<EventDataClass>()
        getData()
        // Use the regular layout for the RecyclerView item
        val adapter = AdapterEventClass(dataList, this)

        recycleView.adapter = adapter

        return binding.root
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = EventDataClass(imageList[i], titleList[i], detailList[i])
            dataList.add(dataClass)
        }
        Log.d("EventFragment", "DataList size: ${dataList.size}")

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_layout, fragment)
        fragmentTransaction.commit()
    }

}
