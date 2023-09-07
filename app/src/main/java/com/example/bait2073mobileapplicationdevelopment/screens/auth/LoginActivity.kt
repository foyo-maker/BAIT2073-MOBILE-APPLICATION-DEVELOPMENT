package com.example.bait2073mobileapplicationdevelopment.screens.auth


import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivityLoginBinding
import com.example.bait2073mobileapplicationdevelopment.databinding.ActivitySignUpBinding
import com.example.bait2073mobileapplicationdevelopment.screens.fragment.MainFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import com.example.bait2073mobileapplicationdevelopment.screens.fragment.StaffMainFragment

class LoginActivity : AppCompatActivity() {
//    var callSignUp: Button? = null
//    var login_btn: Button? = null
//    var image: ImageView? = null
//    var logoText: TextView? = null
//    var sloganText: TextView? = null
//    var username: TextInputLayout? = null
//    var password: TextInputLayout? = null

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //This Line will hide the status bar from the screen


        binding = ActivityLoginBinding.inflate(layoutInflater)
        val layoutPassword: TextInputLayout = binding.layoutPassword
        val eTextPassword: TextInputEditText = binding.eTextPassword
        val layoutEmail: TextInputLayout = binding.layoutEmail
        val eTextEmail:TextInputEditText = binding.eTextEmail
        setContentView(binding.root)


        binding.callSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }
        binding.loginButton.setOnClickListener {

            //customer intent
//            val intent = Intent(this, MainFragment::class.java)
//            startActivity(intent)




            //staff intent
            val intent = Intent(this, StaffMainFragment::class.java)
            startActivity(intent)

        }
        eTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify that the text is about to be changed.
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify that the text has been changed.
                val password = charSequence.toString()


                layoutPassword.error="Weak Password"


            }

            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify that the text has been changed and processed.
            }
        })







    }
}
