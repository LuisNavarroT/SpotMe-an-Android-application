package com.luisnavarro.spotme
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentStopWatchBinding


class StopWatchFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentStopWatchBinding
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var isStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private var timeRemaining: Long = 0
    private var totalTime: Long = 0 // Total time for the timer in milliseconds (60 seconds)
    private var timeRemainingWhenPaused: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timeRemaining = totalTime

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStopWatchBinding.inflate(inflater,container,false)
        sf = requireContext().getSharedPreferences("my_sf_stop_watch", AppCompatActivity.MODE_PRIVATE)

        binding.etHourValue.setText(getData("hours",""))
        binding.etMinuteValue.setText(getData("minutes",""))
        binding.etSecondValue.setText(getData("seconds",""))


        binding.btnHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_stopWatchFragment_to_mainMenuFragment)
        }

        binding.btnStart.setOnClickListener {
            startOrPause()
        }

        binding.btnReset.setOnClickListener {
            reset()
        }
        // Initialize timeRemaining with totalTime when the fragment is created
        timeRemaining = totalTime

        // Update the timer display initially
        updateTimer()

        return binding.root
    }

    private fun startOrPause(){
        if(isStarted==true){
            pause()
        }else{
            start()
        }
    }
    private fun start() {

        // Get the values from the EditText fields
        val hourValue = binding.etHourValue.text.toString().toIntOrNull() ?: 0
        val minuteValue = binding.etMinuteValue.text.toString().toIntOrNull() ?: 0
        val secondValue = binding.etSecondValue.text.toString().toIntOrNull() ?: 0

        // Check if all three values are 0
        if (hourValue == 0 && minuteValue == 0 && secondValue == 0) {
            Toast.makeText(requireContext(), "Please enter a valid duration", Toast.LENGTH_LONG).show()
            return
        }

        if (isStarted) {
            binding.btnStart.text = "Start" // Change the button text to "Start" when pausing
            countDownTimer?.cancel() // Cancel the current timer if it's not null
        } else {
            binding.btnStart.text = "Pause" // Change the button text to "Pause" when starting or resuming
            setInitialTime()
            val startTime = if (timeRemaining > 0) timeRemaining else totalTime

            // Create a new CountDownTimer with the startTime as the initial time
            // Set the initial total time based on the user's input

            countDownTimer = object : CountDownTimer(startTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeRemaining = millisUntilFinished
                    updateTimer()
                }

                override fun onFinish() {
                    binding.btnStart.text = "Start" // Change the button text to "Start" when the timer finishes
                    binding.tvTime.text = "TIME IS UP!"
                    isStarted = false
                }
            }
            countDownTimer?.start() // Start the timer if it's not null
        }

        isStarted = !isStarted // Toggle the value of isStarted

        editor = sf.edit()
        editor.putString("hours",binding.etHourValue.text.toString())
        editor.putString("minutes",binding.etMinuteValue.text.toString())
        editor.putString("seconds",binding.etSecondValue.text.toString())
        editor.apply()
    }

    private fun pause() {
        countDownTimer.cancel()
        binding.btnStart.text = "Start"
        isStarted = false
        timeRemainingWhenPaused = timeRemaining

    }

    private fun setInitialTime() {
        val hourText = binding.etHourValue.text.toString()
        val hourValue = hourText.toIntOrNull() ?: 0

        val minText = binding.etMinuteValue.text.toString()
        val minValue = minText.toIntOrNull() ?: 0

        val secText = binding.etSecondValue.text.toString()
        val secValue = secText.toIntOrNull() ?: 0

        // Calculate the total time in milliseconds
        totalTime = (hourValue * 3600 + minValue * 60 + secValue) * 1000L

    }



    private fun reset() {
        countDownTimer.cancel()
        timeRemaining = totalTime
        binding.btnStart.text = "Start"
        isStarted = !isStarted
        updateTimer()
    }

    private fun updateTimer() {
        val hours = (timeRemaining / 1000) / 3600
        val minutes = ((timeRemaining / 1000) % 3600) / 60
        val seconds = (timeRemaining / 1000) % 60
        binding.tvTime.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    //function to retrieve data from SharedPreferences
    private fun getData(key: String, defaultValue: String): String {
        return sf.getString(key, defaultValue) ?: defaultValue
    }

}