package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.bait2073mobileapplicationdevelopment.R


class StartPlan : Fragment() {
    private lateinit var timer: CountDownTimer
    private var timeProgress = 0
    private var timeSelected : Int = 10
    private var pauseOffSet: Long = 0

    //    private lateinit var binding: ActivityMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_start_plan, container, false)
        val view = inflater.inflate(R.layout.fragment_start_plan, container, false)
        val progressBar = view.findViewById<ProgressBar>(R.id.pbTimer)
        val timeLeftTv = view.findViewById<TextView>(R.id.tvTimeLeft)

        progressBar.progress = timeProgress

        startTimer(timeSelected * 1000 - pauseOffSet * 1000, progressBar, timeLeftTv,view)


        return view
    }

    private fun startTimer(timeInMillis: Long, progressBar: ProgressBar, timeLeftTv: TextView, view: View) {
        timer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(p0: Long) {
                timeProgress++
                pauseOffSet = (timeSelected - p0 / 1000).toLong()
                progressBar.progress = (timeSelected - timeProgress).toInt()
                timeLeftTv.text = (timeSelected - timeProgress).toString()
            }

            override fun onFinish() {
                if (timer!=null){
                    timer!!.cancel()
                    timeProgress=0
                    timeSelected=0
                    pauseOffSet=0


//                    view.findNavController()
//                        .navigate(R.id.action_startPlan_to_play_plan)

                }
                Toast.makeText(requireContext(), "Time's Up!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}