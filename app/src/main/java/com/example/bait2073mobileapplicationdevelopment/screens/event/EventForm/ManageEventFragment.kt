package com.example.bait2073mobileapplicationdevelopment.screens.event.EventForm


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentManageEventBinding
import com.example.bait2073mobileapplicationdevelopment.entities.Event
import com.example.bait2073mobileapplicationdevelopment.screens.event.EventList.EventListFragmentDirections
import com.example.bait2073mobileapplicationdevelopment.screens.event.EventList.EventListViewModel
import com.squareup.picasso.Callback

import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ManageEventFragment : Fragment() {


    private lateinit var viewModel: EventFormViewModel
    private lateinit var binding: FragmentManageEventBinding
    private val PICK_IMAGE_REQUEST = 1
    private val CAPTURE_IMAGE_REQUEST = 2
    private var selectedImageBitmap: Bitmap? = null
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //val event_id = intent.getStringExtra("event_id")
        binding = FragmentManageEventBinding.inflate(inflater, container, false)

        initViewModel()
        createEventObservable()
        val args = ManageEventFragmentArgs.fromBundle(requireArguments())
        val event_id = args.eventId

        if (event_id != 0) {
            loadEventData(event_id)
        }


        binding.createEventButton.setOnClickListener {
            createEvent(event_id,selectedImageBitmap)
        }


        binding.uploadButton.setOnClickListener {
//            pickImage()
            showImagePickerDialog()
        }

        return binding.root
    }

    private fun pickImage() {
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        startActivityForResult(intent, 101)

    }

    // Function to show a dialog for choosing between gallery and camera
    private fun showImagePickerDialog() {
        val options = arrayOf("Gallery", "Camera")

        AlertDialog.Builder(requireContext())
            .setTitle("Choose an option")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openGallery()
                    1 -> openCamera()
                }
            }
            .show()
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAPTURE_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    val selectedImageUri = data?.data
                    if (selectedImageUri != null) {


                        binding.eventImgAdmin.setImageURI(selectedImageUri)
                        val imageBitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectedImageUri)
                        selectedImageBitmap = imageBitmap
                    }
                }

                CAPTURE_IMAGE_REQUEST -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap?
                    if (imageBitmap != null) {
                        selectedImageBitmap = imageBitmap
                    }
                }
            }
        }
    }
    private fun loadEventData(event_id: Int?) {
        viewModel.getLoadEventObservable().observe(viewLifecycleOwner, Observer<Event?> { event ->
            if (event != null) {
                binding.eTextTitle.setText(event.title)
                binding.eTextDetails.setText(event.details)
                binding.createEventButton.text = "Update"
                binding.createEventTitletv.text = "Update Event"
                binding.eTextAddress.setText(event.address.toString())

                if (!event.date.isNullOrEmpty()) {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val date = dateFormat.parse(event.date)

                    if (date != null) {
                        val calendar = Calendar.getInstance()
                        calendar.time = date

                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)
                        val day = calendar.get(Calendar.DAY_OF_MONTH)
                        val hour = calendar.get(Calendar.HOUR_OF_DAY)
                        val minute = calendar.get(Calendar.MINUTE)

                        // Set the date and time to your DatePicker and TimePicker
                        binding.datePicker1.updateDate(year, month, day)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            binding.timePicker1.hour = hour
                            binding.timePicker1.minute = minute
                        } else {
                            // For older Android versions
                            binding.timePicker1.currentHour = hour
                            binding.timePicker1.currentMinute = minute
                        }
                    }
                }

                val eventImageView = binding.eventImgAdmin
                if (!event.image.isNullOrBlank()) {
                    Picasso.get()
                        .load(event.image)
                        .fit()
                        .error(R.drawable.img_person)
                        .into(eventImageView, object : Callback {
                            override fun onSuccess() {
                                Log.e("NICE", "image")
                            }
                            override fun onError(e: Exception?) {
                                Log.e("NotNice", "${e}")

                            }
                        })
                } else {
                    Log.e("noimage", "noimage")
                    Picasso.get().load(R.drawable.img_person).into(eventImageView)
                }
            }
        })
        viewModel.getEventData(event_id)
    }




    private fun createEvent(event_id: Int?, selectedImageBitmap: Bitmap?) {

        val imageData: String? = if (selectedImageBitmap != null) {
            encodeBitmapToBase64(selectedImageBitmap)
        } else {
            null
        }

        val datePicker = binding.datePicker1
        val timePicker = binding.timePicker1

        val year = datePicker.year
        val month = datePicker.month + 1
        val day = datePicker.dayOfMonth

        val hourOfDay: Int
        val minute: Int

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hourOfDay = timePicker.hour
            minute = timePicker.minute
        } else {
            hourOfDay = timePicker.currentHour
            minute = timePicker.currentMinute
        }

        val formattedDate = String.format("%04d-%02d-%02d %02d:%02d:00", year, month, day, hourOfDay, minute)
        val event = Event(
            null,
            binding.eTextTitle.text.toString(),
            binding.eTextDetails.text.toString(),
            imageData,
            formattedDate,
            binding.eTextAddress.text.toString(),
        )

        if (event_id == 0)
            viewModel.createEvent(event)
        else
            viewModel.updateEvent(event_id ?: 0, event)
    }


    private fun encodeBitmapToBase64(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArray = baos.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EventFormViewModel::class.java)


    }

    private fun createEventObservable() {
        viewModel.getCreateNewEventObservable().observe(viewLifecycleOwner, Observer<Event?> {
            if (it == null) {
                ManageEventFragmentDirections.actionManageEventFragmentToEventListFragment()
            } else {
                showSuccessDialog()
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
            val action = ManageEventFragmentDirections.actionManageEventFragmentToEventListFragment()
            findNavController().navigate(action)
        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show()


    }
}