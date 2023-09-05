package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivitySignUpBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentProfileBinding
import com.example.bait2073mobileapplicationdevelopment.screens.profile.RequestGenderActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class ProfileFragment : Fragment() {


    private lateinit var dialog: Dialog
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentProfileBinding.inflate(inflater, container, false)


        validateOnChangeUserName()
        validateOnChangeEmail()
        validateOnChangeUserWeight()
        validateOnChangeUserHeight()

//        emailFocusListener()

        binding.saveButton.setOnClickListener {

            if (validateForm()) {


                successDialog()
            }
        }

        return binding.root
    }

    private fun successDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_success)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false) // Optional
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations =
            R.style.DialogAnimation // Setting the animations to dialog

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {
            Log.i("cancel", "gg")
            Toast.makeText(requireContext(), "Okay", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }


        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show() // Showing the dialog here
    }


    private fun validateForm(): Boolean {

        //get the input
        val emailText = binding.eTextEmail.text.toString()
        val usernameText = binding.eTextUserName.text.toString()
        val weightText = binding.eTextWeight.text.toString()
        val heightText = binding.eTextHeight.text.toString()

        //get the layout component
        val layoutEmail: TextInputLayout = binding.layoutEmail
        val layoutUserName: TextInputLayout = binding.layoutUsername
        val layoutWeight: TextInputLayout = binding.layoutWeight
        val layoutHeight: TextInputLayout = binding.layoutHeight



        val selectedGender = when (binding.radioGroupGender.checkedRadioButtonId) {
            R.id.radioMale -> "Male"
            R.id.radioFemale -> "Female"
            else -> null
        }
        //get the error
        val emailError = validEmail(emailText)
        val nameError = validUserName(usernameText)
        val weightError = validWeight(weightText)
        val heightError = validHeight(heightText)
        val genderError = if (selectedGender == null) "Gender is required" else null

        //set default to true
        var isValidate = true



        //validate
        if (emailError != null) {
            layoutEmail.error = emailError
            isValidate = false
        }
        if (nameError != null) {
            layoutUserName.error = nameError
            isValidate = false
        }

        if (weightError != null) {
            layoutWeight.error = weightError
            isValidate = false
        }else{
            layoutWeight.error =null
        }
        if (heightError != null) {
            layoutHeight.error = heightError
            isValidate = false
        }else{
            layoutHeight.error =null
        }
        if (genderError != null) {
            // Show the error for gender
            binding.genderError.text = genderError
            binding.genderError.visibility = View.VISIBLE
            isValidate = false
        } else {
            binding.genderError.visibility = View.GONE
        }


        return isValidate


    }



//    private fun emailFocusListener() {
//        binding.eTextEmail.setOnFocusChangeListener { _, focused ->
//            if (!focused) {
//                val emailError = validEmail(binding.eTextEmail.text.toString())
//                if (emailError != null) {
//                    binding.layoutEmail.error = emailError
//                }else{
//                    binding.layoutEmail.error = null
//                }
//
//
//            }
//        }
//    }


    private fun validateOnChangeEmail() {

        val layoutEmail: TextInputLayout = binding.layoutEmail
        val eTextEmail: TextInputEditText = binding.eTextEmail
        eTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This method is called to notify that the text is about to be changed.
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // This method is called to notify that the text has been changed.
                val emailText = charSequence.toString()
                val error = validEmail(emailText)

                if (error != null) {
                    layoutEmail.error = error
                } else {
                    layoutEmail.error = ""
                }

            }

            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })
    }


    private fun validateOnChangeUserName() {
        val eTextUserName = binding.eTextUserName
        val layoutUserName: TextInputLayout = binding.layoutUsername
        eTextUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This method is called to notify that the text is about to be changed.
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // This method is called to notify that the text has been changed.
                val userNameText = charSequence.toString()
                val error = validUserName(userNameText)

                if (error != null) {
                    layoutUserName.error = error
                } else {
                    layoutUserName.error = ""
                }

            }

            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })
    }


    private fun validateOnChangeUserHeight() {

        val layoutHeight: TextInputLayout = binding.layoutHeight
        val eTextUserHeight = binding.eTextHeight

        eTextUserHeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This method is called to notify that the text is about to be changed.
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // This method is called to notify that the text has been changed.
                val userNameText = charSequence.toString()
                val error = validHeight(userNameText)

                if (error != null) {
                    layoutHeight.error = error
                } else {
                    layoutHeight.error = ""
                }

            }

            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })
    }

    private fun validateOnChangeUserWeight() {

        val layoutWeight: TextInputLayout = binding.layoutWeight
        val eTextUserWeight = binding.eTextWeight

        eTextUserWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This method is called to notify that the text is about to be changed.
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // This method is called to notify that the text has been changed.
                val userNameText = charSequence.toString()
                val error = validWeight(userNameText)

                if (error != null) {
                    layoutWeight.error = error
                } else {
                    layoutWeight.error = ""
                }

            }

            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })
    }



    private fun validEmail(emailText: String): String? {
        if (emailText == "") {
            return "Email Is Required"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }


    private fun validWeight(weightText: String): String? {
        if (weightText.isBlank()) {
            return "Weight is required"
        } else if (!weightText.matches("^(\\d{2,3}(\\.\\d{1,2})?|\\d{1}(\\.\\d{1,2})?)$".toRegex())) {
            return "Invalid weight format. Enter in the format 'XX', 'XXX', 'XX.XX', 'XX.X', 'XXX.X', or 'XXX.XX'."
        }
        return null
    }

    private fun validHeight(heightText: String): String? {
        if (heightText.isBlank()) {
            return "Height is required"
        } else if (!heightText.matches("^(\\d{2,3}(\\.\\d{1,2})?|\\d{1}(\\.\\d{1,2})?)$".toRegex())) {
            return "Invalid height format. Enter in the format 'XX', 'XXX', 'XX.XX', 'XX.X', 'XXX.X', or 'XXX.XX'."
        }
        return null
    }


    private fun validUserName(nameText: String): String? {

        if (nameText == "") {
            return "Name Is Required"
        } else if (nameText.length > 10) {
            return "Invalid Name"
        }
        return null
    }


}

