package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.bait2073mobileapplicationdevelopment.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [play_plan.newInstance] factory method to
 * create an instance of this fragment.
 */
class play_plan : Fragment() {
    private lateinit var timer: CountDownTimer
    private var timeProgress = 0
    private var timeSelected : Int = 20
    private var pauseOffSet: Long = 0
    private var isStart = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play_plan, container, false)
        val timeLeftTv = view.findViewById<TextView>(R.id.timerCount)
        startTimer(timeSelected * 1000 - pauseOffSet * 1000, timeLeftTv,view)
        return view
    }

    private fun startTimer(timeInMillis: Long, timeLeftTv: TextView, view: View) {
        timer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(p0: Long) {
                timeProgress++
                pauseOffSet = (timeSelected - p0 / 1000).toLong()

                timeLeftTv.text = "00:"+(timeSelected - timeProgress).toString()
            }

            override fun onFinish() {
                if (timer!=null){
                    timer!!.cancel()
                    timeProgress=0
                    timeSelected=0
                    pauseOffSet=0


//                    view.findNavController()
//                        .navigate(R.id.action_startPlan_to_fragment_play_plan)

                }
                Toast.makeText(requireContext(), "Time's Up!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
//    private fun startTimerSetup()
//    {
//        val startBtn: Button = findViewById(R.id.pauseBtn)
//        if (timeSelected>timeProgress)
//        {
//            if (isStart)
//            {
//                startBtn.text = "Pause"
//                startTimer(pauseOffSet)
//                isStart = false
//            }
//            else
//            {
//                isStart =true
//                startBtn.text = "Resume"
//                timePause()
//            }
//        }
//
//    }

    private fun timePause()
    {
        if (timer!=null)
        {
            timer!!.cancel()
        }
    }

//    private fun startTimer(pauseOffSetL: Long)
//    {
//        val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
//        progressBar.progress = timeProgress
//        timeCountDown = object :CountDownTimer(
//            (timeSelected*1000).toLong() - pauseOffSetL*1000, 1000)
//        {
//            override fun onTick(p0: Long) {
//                timeProgress++
//                pauseOffSet = timeSelected.toLong()- p0/1000
//                progressBar.progress = timeSelected-timeProgress
//                val timeLeftTv:TextView = findViewById(R.id.tvTimeLeft)
//                timeLeftTv.text = (timeSelected - timeProgress).toString()
//            }
//
//            override fun onFinish() {
//                resetTime()
//                Toast.makeText(this@MainActivity,"Times Up!", Toast.LENGTH_SHORT).show()
//            }
//
//        }.start()
//    }
}