import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.example.bait2073mobileapplicationdevelopment.R

class LoginTabFragment : Fragment() {
    private lateinit var loginEmailEditText: EditText
    private lateinit var loginPasswordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var emailErrorText: TextView
    private lateinit var passwordErrorText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login_tab, container, false)

        loginEmailEditText = rootView.findViewById(R.id.login_email)
        loginPasswordEditText = rootView.findViewById(R.id.login_password)
        loginButton = rootView.findViewById(R.id.login_button)
        emailErrorText = rootView.findViewById(R.id.email_error_text)
        passwordErrorText = rootView.findViewById(R.id.password_error_text)

        // Set click listener for the login button
        loginButton.setOnClickListener {
            validateLoginFields()
        }

        return rootView
    }

    //testing
    private fun validateLoginFields() {
        val email = loginEmailEditText.text.toString().trim()
        val password = loginPasswordEditText.text.toString()

        // Check if email field is empty
        if (email.isEmpty()) {
            setErrorForEditText(loginEmailEditText, "Please enter your email.")
            return
        }

        // Check if email is valid
        if (!isValidEmail(email)) {
            setErrorForEditText(loginEmailEditText, "Invalid email.")
            return
        }

        // Check if password field is empty
        if (password.isEmpty()) {
            setErrorForEditText(loginPasswordEditText, "Please enter your password.")
            return
        }
//AA
        // Check if password is at least 6 characters long
        if (password.length < 6) {
            setErrorForEditText(
                loginPasswordEditText,
                "Password should be at least 6 characters long."
            )
            return
        }

        // If all validations pass, you can proceed with your login logic here
        // For example, call a login API or perform authentication

        // Show success message as an example
        showSnackbar("Login successful!")

        // Clear input fields after successful login
        loginEmailEditText.text.clear()
        loginPasswordEditText.text.clear()
    }

    private fun setErrorForEditText(editText: EditText, errorMessage: String) {
        editText.error = errorMessage
        val redBorderDrawable = GradientDrawable()
        redBorderDrawable.shape = GradientDrawable.RECTANGLE
        redBorderDrawable.setStroke(2, resources.getColor(android.R.color.holo_red_dark))

        // Check if the EditText is loginEmailEditText and set the background accordingly
        if (editText == loginEmailEditText) {
            if (errorMessage.isNotEmpty()) {
                editText.background = resources.getDrawable(R.drawable.errortext_bkg)
                emailErrorText.visibility = View.VISIBLE
                emailErrorText.text = errorMessage
            } else {
                editText.background = redBorderDrawable
                emailErrorText.visibility = View.GONE
            }
        } else if (editText == loginPasswordEditText) {
            // You can add similar handling for loginPasswordEditText if needed
            // For example, set a different background when the password has an error
            if (errorMessage.isNotEmpty()) {
                editText.background = resources.getDrawable(R.drawable.errortext_bkg)
                passwordErrorText.visibility = View.VISIBLE
                passwordErrorText.text = errorMessage
            } else {
                editText.background = redBorderDrawable
                passwordErrorText.visibility = View.GONE
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showSnackbar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }
    }
}