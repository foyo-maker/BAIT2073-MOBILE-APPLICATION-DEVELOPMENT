package com.example.bait2073mobileapplicationdevelopment.screens.fragment


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.R

class CustomerListFragment : Fragment() {

    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val showDialog: ImageView = view.findViewById(R.id.delete_img)

        // Create the Dialog here
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_warning)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false) // Optional
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Setting the animations to dialog

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

        showDialog.setOnClickListener {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
            dialog.show() // Showing the dialog here
        }

        okay.setBackgroundColor(requireContext().getColor(android.R.color.holo_red_light))
    }
}
