package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.screens.admin.UserForm.UserFormFragmentDirections
import com.example.bait2073mobileapplicationdevelopment.screens.auth.Login.LoginActivity


class LogoutFragment : Fragment() {


    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        showLogoutConfirmationDialog()

        return inflater.inflate(R.layout.fragment_logout, container, false)
    }


    private fun showLogoutConfirmationDialog(){
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_logout)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false) // Optional
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Setting the animations to dialog

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {

            dialog.dismiss()



            // Clear user data from SharedPreferences
            clearUserDataFromSharedPreferences(requireContext())

            // Navigate to LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)


        }

        cancel.setOnClickListener {

            val intent = Intent(requireContext(), MainFragment::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show() // Showing the dialog here


    }


    private fun clearUserDataFromSharedPreferences(context: Context) {

        Log.e("clearing", "clearing")
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // This will remove all data stored in the SharedPreferences under "UserData"
        editor.apply()
    }
}