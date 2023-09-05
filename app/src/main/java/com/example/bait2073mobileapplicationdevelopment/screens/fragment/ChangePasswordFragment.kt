package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentChangePasswordBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentProfileBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ChangePasswordFragment : Fragment() {




    private lateinit var dialog: Dialog
    private lateinit var binding:FragmentChangePasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)

//        emailFocusListener()
        validateOnChangeNewPassword()
        validateOnChangeNewConfirmPassword()
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
        val passwordText = binding.eTextNewPassword.text.toString()
        val confirmPassText = binding.eTextConfirmPassword.text.toString()
        val layoutNewPassword: TextInputLayout = binding.layoutNewPassword
        val layoutConfirmPass: TextInputLayout = binding.layoutConfirmPassword

        val newPasswordError = validNewPassword(passwordText)
        val confirmPasswordError = validConfirmPass(passwordText, confirmPassText)

        var isValidate = true
        if (newPasswordError != null) {
            layoutNewPassword.error = newPasswordError
            isValidate = false
        }

        if (confirmPasswordError != null) {
            layoutConfirmPass.error = confirmPasswordError
            isValidate = false
        }

        return isValidate


    }



    private fun validateOnChangeNewPassword() {

        val layoutNewPassword: TextInputLayout = binding.layoutNewPassword
        val eTextNewPassword: TextInputEditText = binding.eTextNewPassword
        eTextNewPassword.addTextChangedListener(object : TextWatcher {
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
                val passwordText = charSequence.toString()
                val error = validNewPassword(passwordText)

                if (error != null) {
                    layoutNewPassword.error = error
                } else {
                    layoutNewPassword.error = ""
                }
            }

            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })
    }
    private fun validateOnChangeNewConfirmPassword() {
        val layoutConfirmPass: TextInputLayout = binding.layoutConfirmPassword
        val eTextConfirmPass: TextInputEditText = binding.eTextConfirmPassword
        val eTextPass: TextInputEditText = binding.eTextNewPassword
        eTextConfirmPass.addTextChangedListener(object : TextWatcher {
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
                val confirmPassText = charSequence.toString()
                val error = validConfirmPass(eTextPass.text.toString(), confirmPassText)

                if (error != null) {
                    layoutConfirmPass.error = error
                } else {
                    layoutConfirmPass.error = ""
                }

            }

            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })
    }

    private fun validNewPassword(passwordText: String): String? {

        if (passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }
    private fun validConfirmPass(passText: String, confirmPassText: String): String? {

        if (confirmPassText == "") {
            return "Confirm Password Is Required"
        } else if (passText != confirmPassText) {
            return "Confirm Password Must Same With Password"
        }
        return null
    }












}