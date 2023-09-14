package com.example.bait2073mobileapplicationdevelopment.screens.password.RequestCode

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityRequestCodeBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityRequestEmailBinding
import com.example.bait2073mobileapplicationdevelopment.entities.AutenticateEmailUser
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.screens.auth.Login.LoginActivity
import com.example.bait2073mobileapplicationdevelopment.screens.password.ForgetPassword.ForgetPasswordActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RequestCodeActivity  : AppCompatActivity() {
//    var callSignUp: Button? = null
//    var login_btn: Button? = null
//    var image: ImageView? = null
//    var logoText: TextView? = null
//    var sloganText: TextView? = null
//    var username: TextInputLayout? = null
//    var password: TextInputLayout? = null

    private lateinit var binding: ActivityRequestCodeBinding

    private lateinit var dialog: Dialog

    private lateinit var user: AutenticateEmailUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //This Line will hide the status bar from the screen


        binding = ActivityRequestCodeBinding.inflate(layoutInflater)



        setContentView(binding.root)


        try{
            user = intent.getSerializableExtra("user") as AutenticateEmailUser
        }catch(e :Exception){
            e.printStackTrace()
        }
        validateOnChangeCode()

        binding.callLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        binding.submitButton.setOnClickListener {

            if (validateForm()) {
                if(binding.eTextCode.text.toString().toInt() == user.code){
                    val intent = Intent(this@RequestCodeActivity, ForgetPasswordActivity::class.java)
                    intent.putExtra("email",user.email)
                    startActivity(intent)
                }

            }
        }




    }


    private fun validateForm(): Boolean {
        var isValidate = true
        val codeText = binding.eTextCode.text.toString()
        val layoutCode: TextInputLayout = binding.layoutCode
        if (codeText == "") {
            layoutCode.error = "Code Is Required"
            isValidate = false
        }
        else if(codeText.toString().toInt()!= user.code){
            layoutCode.error = "Incorrect Code"
            isValidate = false
        }
        return isValidate


    }




    private fun validateOnChangeCode() {

        val layoutCode: TextInputLayout = binding.layoutCode
        val eTextCode: TextInputEditText = binding.eTextCode
        eTextCode.addTextChangedListener(object : TextWatcher {
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


                layoutCode.error = ""
            }


            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })
    }
}
