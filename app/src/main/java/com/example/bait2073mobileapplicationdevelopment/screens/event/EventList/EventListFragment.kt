package com.example.bait2073mobileapplicationdevelopment.screens.event.EventList

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bait2073mobileapplicationdevelopment.BaseActivity
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.EventAdapter
import com.example.bait2073mobileapplicationdevelopment.adapter.EventListAdapter
import com.example.bait2073mobileapplicationdevelopment.dao.EventDao
import com.example.bait2073mobileapplicationdevelopment.database.EventDatabase
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentEventListBinding
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.interfaces.GetEventDataService
import com.example.bait2073mobileapplicationdevelopment.repository.EventRepository
import com.example.bait2073mobileapplicationdevelopment.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class EventListFragment: Fragment(), EventListAdapter.EventClickListerner, PopupMenu.OnMenuItemClickListener {

    lateinit var recyclerViewAdapter: EventListAdapter
    lateinit var viewModel: EventListViewModel

//    lateinit var api: GetEventDataService
//    lateinit var db: EventDatabase

    private lateinit var binding:FragmentEventListBinding
    lateinit var selectedEvent : Event
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEventListBinding.inflate(inflater, container, false)
        initViewModel()
        initRecyclerView()
        observeEventDeletion()
        searchEvent()


        binding.addEventBtn.setOnClickListener {

            val action =
                EventListFragmentDirections.actionEventListFragmentToManageEventFragment(0)
            this.findNavController().navigate(action)
        }


        binding.apply {

            recycleView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }



            return binding.root
        }
    }
    private fun searchEvent(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?) :Boolean{
                return false
            }

            override fun onQueryTextChange(newText:String?):Boolean{
                if(newText!=null){
                    recyclerViewAdapter.filterList(newText)
                }
                return true
            }
        })
    }

    private fun initRecyclerView(){
        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        recyclerViewAdapter = EventListAdapter(requireContext(),this)
        binding.recycleView.adapter = recyclerViewAdapter
    }




    fun initViewModel(){
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(EventListViewModel::class.java)

        viewModel.getEventListObserverable().observe(viewLifecycleOwner,Observer<List<Event?>>{
            eventListResponse->
            if (eventListResponse==null){
                Toast.makeText(requireContext(),"EventListFragment initViewModel() no result found...",Toast.LENGTH_LONG).show()
            }else{
                val eventList = eventListResponse.filterNotNull().toMutableList()
                Log.i("EventListFragment","initViewModel:\n"+"$eventList")
                recyclerViewAdapter.updateList(eventList)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getEvents()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1000){
            viewModel.getEvents()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onItemClicked(event: Event) {
        val action=EventListFragmentDirections.actionEventListFragmentToManageEventFragment(event.id?:0)
        this.findNavController().navigate(action)
    }

    override fun onLongItemClicked(event: Event, cardView: CardView) {
        selectedEvent=event
        popUpDisplay(cardView)
    }



    private fun popUpDisplay(cardView:CardView){
        val popup = PopupMenu(requireContext(),cardView)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.delete_note){
            viewModel.deleteEvent(selectedEvent)
        }
        return false
    }

    private fun observeEventDeletion(){
        viewModel.getDeleteEventObservable().observe(viewLifecycleOwner,Observer<Event?>{
            deletedUser ->
            if(deletedUser == null){
               Toast.makeText(requireContext(), "Cannot Delete Event", Toast.LENGTH_SHORT).show()
            }else{
                showSuccessDialog()
                viewModel.getEvents()
            }
        })
    }

    private fun showSuccessDialog(){
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_success)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {
            dialog.dismiss()
        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show()
    }


}