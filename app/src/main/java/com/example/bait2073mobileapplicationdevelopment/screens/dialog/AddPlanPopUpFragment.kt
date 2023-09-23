package com.example.bait2073mobileapplicationdevelopment.screens.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.bait2073mobileapplicationdevelopment.R
import com.google.android.material.textfield.TextInputLayout


class AddPlanPopUpFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_add_plan_pop_up, null)
        val planNameInput = dialogView.findViewById<TextInputLayout>(R.id.PlanNameInput)
        val planNameEditText = planNameInput.editText

        builder.setView(dialogView)
            .setPositiveButton("Add Plan") { dialog, _ ->
                val planNameValue = planNameEditText?.text.toString()

                // Handle the planName as needed
                if (planNameValue.isNotEmpty()) {
                    Log.e("value", "$planNameValue")

                    // You can do something with the planNameValue here
                } else {
                    Toast.makeText(requireContext(), "Plan name is empty", Toast.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }
}
