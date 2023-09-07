package com.example.bait2073mobileapplicationdevelopment.screens.staff.customer


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
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
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentCustomerFormBinding
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream


class CreateCustomerFragment : Fragment() {


    private lateinit var viewModel: CreateCustomerViewModel
    private lateinit var binding: FragmentCustomerFormBinding
    private val PICK_IMAGE_REQUEST = 1
    private val CAPTURE_IMAGE_REQUEST = 2
    private var selectedImageBitmap: Bitmap? = null
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //val user_id = intent.getStringExtra("user_id")
        binding = FragmentCustomerFormBinding.inflate(inflater, container, false)

        initViewModel()
        createUserObservable()
        val args = CreateCustomerFragmentArgs.fromBundle(requireArguments())
        val customer_id = args.customerId

        if (customer_id != 0) {
            loadUserData(customer_id)
        }


        binding.createButton.setOnClickListener {
            createUser(customer_id,selectedImageBitmap)
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

    // Function to open the camera for image capture
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAPTURE_IMAGE_REQUEST)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == Activity.RESULT_OK) {
//
//                if(requestCode == 101){
//                    val url = data?.data
//                    binding.profileImg.setImageURI(url)
//                }
//            }
//        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    val selectedImageUri = data?.data
                    if (selectedImageUri != null) {


                    binding.profileImg.setImageURI(selectedImageUri)
                        val imageBitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectedImageUri)
                        selectedImageBitmap = imageBitmap // Store the selected image in the variable
                    }
                }

                CAPTURE_IMAGE_REQUEST -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap?
                    // Handle the captured image bitmap (e.g., display it, store it, etc.)
                    if (imageBitmap != null) {
                        selectedImageBitmap = imageBitmap // Store the captured image in the variable
                    }
                }
            }
        }
    }
    private fun loadUserData(user_id: Int?) {
        viewModel.getLoadUserObservable().observe(viewLifecycleOwner, Observer<User?> {
            if (it != null) {

                binding.eTextUserName.setText(it.name)
                binding.eTextEmail.setText(it.email)
                binding.createButton.setText("Update")
                binding.createTitletv.setText("Update User")
                binding.eTextWeight.setText(it.weight.toString())
                binding.eTextHeight.setText(it.height.toString())
                val custImageView = binding.profileImg

                if (!it.image.isNullOrBlank()) {

                    Picasso.get().load(it.image).fit().into(custImageView)
                } else{
                    // If no image URL is available,  set a placeholder image or handle this case as needed.\
                    Log.e("noimage", "noimage")
                    Picasso.get().load(R.drawable.img_person).into(custImageView)
                }
                if (it.gender == "Male") {
                    binding.radioMale.isChecked = true
                } else {
                    // Optionally, you can uncheck it here if "it.gender" is not "Male"
                    binding.radioFemale.isChecked = true
                }
            }
        })
        viewModel.getUserData(user_id)
    }

    private fun createUser(user_id: Int?,selectedImageBitmap: Bitmap?) {


        val imageData: String? = if (selectedImageBitmap != null) {
            encodeBitmapToBase64(selectedImageBitmap)
        } else {
            null
        }

        val selectedGender = when (binding.radioGroupGender.checkedRadioButtonId) {
            R.id.radioMale -> "Male"
            R.id.radioFemale -> "Female"
            else -> null
        }
        val user = User(
            null,
            binding.eTextUserName.text.toString(),
            binding.eTextEmail.text.toString(),
            selectedGender,
            imageData,
            "",
            "",
            binding.eTextWeight.text.toString().toDoubleOrNull(),
            binding.eTextHeight.text.toString().toDoubleOrNull()
        )

        if (user_id == 0)
            viewModel.createUser(user)
        else
            viewModel.updateUser(user_id ?: 0, user)

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
        ).get(CreateCustomerViewModel::class.java)

    }

    private fun createUserObservable() {
        viewModel.getCreateNewUserObservable().observe(viewLifecycleOwner, Observer<User?> {
            if (it == null) {
                CreateCustomerFragmentDirections.actionCreateCustomerFragementToCustomerListFragment()
            } else {


                showSuccessDialog()




            }
        })
    }

    private fun showSuccessDialog(){
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_success)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false) // Optional
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Setting the animations to dialog

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {

            dialog.dismiss()
            val action = CreateCustomerFragmentDirections.actionCreateCustomerFragementToCustomerListFragment()
            findNavController().navigate(action)

        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show() // Showing the dialog here


    }
}
