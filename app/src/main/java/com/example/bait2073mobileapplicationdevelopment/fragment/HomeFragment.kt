import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.fragment.WorkoutFragment

class HomeFragment : Fragment() {

    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val showDialog: ImageView = view.findViewById(R.id.icon_add)

        // Create the Dialog here
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_notification)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false) // Optional
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Setting the animations to dialog

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {
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

        val personalizedRecommendCard: CardView = view.findViewById(R.id.personalizedRecommend)
        personalizedRecommendCard.setOnClickListener {
            // Replace the current fragment with the WorkoutFragment
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_layout, WorkoutFragment())
            fragmentTransaction.addToBackStack(null) // Add to back stack if you want to navigate back
            fragmentTransaction.commit()
        }
    }
}
