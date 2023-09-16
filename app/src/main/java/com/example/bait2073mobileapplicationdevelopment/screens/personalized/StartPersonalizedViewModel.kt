package com.example.bait2073mobileapplicationdevelopment.screens.personalized

import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.PersonalizedWorkout

class StartPersonalizedViewModel : ViewModel() {
    // The current word
    // The current word


    private val workoutList = ArrayList<PersonalizedWorkout>()


    private val _activityName= MutableLiveData<String>()

    val activityName: LiveData<String>
        get() = _activityName
    // The current score
    private val _activityCount = MutableLiveData<Int>()
    val activityCount: LiveData<Int>
        get() = _activityCount


    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime
    // The list of words - the front of the list is the next word to guess



    private val _gifImageUrl = MutableLiveData<String>()
    val gifImageUrl: LiveData<String>
        get() = _gifImageUrl


    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    private lateinit var activityList: MutableList<String>
    private lateinit var gifImageList: MutableList<String>

    private val timer: CountDownTimer
    private var timeProgress = 0
    private var timeSelected : Int = 10
    private var pauseOffSet: Long = 0

//     The String version of the current time
//    val currentTimeString = Transformations.map(currentTime) { time ->
//        DateUtils.formatElapsedTime(time)
//    }
    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {

        Log.e("resetlist", "$workoutList")
        activityList.clear()
        for (workout in workoutList) {
            activityList.add(workout.name)
        }
        Log.e("activitylist", "$activityList")
        gifImageList.clear()

        for (workout in workoutList) {
            gifImageList.add(workout.gifimage!!)
        }


    }

    init {

        _activityName.value = ""
        _activityCount.value = 1
        activityList = mutableListOf()
        gifImageList = mutableListOf()
        val progressBar = view.findViewById<ProgressBar>(R.id.pbTimer)
        Log.i("GameViewModel", "GameViewModel created!")

//         Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(timeSelected * 1000 - pauseOffSet * 1000, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long)
            {
                _currentTime.value = millisUntilFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE

                nextActivity()

                if(activityList.isEmpty())
                onGameFinish()
            }
        }

        timer.start()
    }
    fun updateList(newList : List<PersonalizedWorkout>){
        workoutList.clear()
        workoutList.addAll(newList)
        Log.e("update", "$workoutList")
        resetList()
        nextActivity()
    }
    /**
     * Moves to the next word in the list
     */
    private fun nextActivity() {
        // Shuffle the word list, if the list is empty
        if (activityList.isEmpty()) {
            onGameFinish()
        } else {
            // Remove a word from the list
            _activityName.value = activityList.removeAt(0)
            _gifImageUrl.value = gifImageList.removeAt(0)
            timer.start()
        }
    }


    companion object {

        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L

    }

    /** Methods for buttons presses **/
    fun onSkip() {

        nextActivity()

        _activityCount.value = (activityCount.value)?.plus(1)
    }



    // Event which triggers the end of the game



    override fun onCleared() {
        super.onCleared()
        // Cancel the timer
//        timer.cancel()
    }



    //method for game completed event

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }
}