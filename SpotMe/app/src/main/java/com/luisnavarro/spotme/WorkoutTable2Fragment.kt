package com.luisnavarro.spotme

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentWorkoutTable2Binding
import com.luisnavarro.spotme.databinding.FragmentWorkoutTableBinding


class WorkoutTable2Fragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentWorkoutTable2Binding
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutTable2Binding.inflate(inflater,container,false)
        sf = requireContext().getSharedPreferences("my_sf2", AppCompatActivity.MODE_PRIVATE)

        binding.etNameDay2.setText(getData("title1",""))

        binding.etExercise1.setText(getData("exercise1", ""))
        binding.etSets1.setText(getData("sets1", ""))
        binding.etReps1.setText(getData("reps1", ""))
        binding.etWeight1.setText(getData("weight1", ""))
        binding.etAchieved1.setText(getData("achieved1", ""))
        binding.etTips1.setText(getData("tips1", ""))
        binding.etExtra1.setText(getData("extra1", ""))

        binding.etExercise2.setText(getData("exercise2", ""))
        binding.etSets2.setText(getData("sets2", ""))
        binding.etReps2.setText(getData("reps2", ""))
        binding.etWeight2.setText(getData("weight2", ""))
        binding.etAchieved2.setText(getData("achieved2", ""))
        binding.etTips2.setText(getData("tips2", ""))
        binding.etExtra2.setText(getData("extra2", ""))

        binding.etExercise3.setText(getData("exercise3", ""))
        binding.etSets3.setText(getData("sets3", ""))
        binding.etReps3.setText(getData("reps3", ""))
        binding.etWeight3.setText(getData("weight3", ""))
        binding.etAchieved3.setText(getData("achieved3", ""))
        binding.etTips3.setText(getData("tips3", ""))
        binding.etExtra3.setText(getData("extra3", ""))

        binding.etExercise4.setText(getData("exercise4", ""))
        binding.etSets4.setText(getData("sets4", ""))
        binding.etReps4.setText(getData("reps4", ""))
        binding.etWeight4.setText(getData("weight4", ""))
        binding.etAchieved4.setText(getData("achieved4", ""))
        binding.etTips4.setText(getData("tips4", ""))
        binding.etExtra4.setText(getData("extra4", ""))

        binding.etExercise5.setText(getData("exercise5", ""))
        binding.etSets5.setText(getData("sets5", ""))
        binding.etReps5.setText(getData("reps5", ""))
        binding.etWeight5.setText(getData("weight5", ""))
        binding.etAchieved5.setText(getData("achieved5", ""))
        binding.etTips5.setText(getData("tips5", ""))
        binding.etExtra5.setText(getData("extra5", ""))

        binding.etExercise6.setText(getData("exercise6", ""))
        binding.etSets6.setText(getData("sets6", ""))
        binding.etReps6.setText(getData("reps6", ""))
        binding.etWeight6.setText(getData("weight6", ""))
        binding.etAchieved6.setText(getData("achieved6", ""))
        binding.etTips6.setText(getData("tips6", ""))
        binding.etExtra6.setText(getData("extra6", ""))

        binding.etExercise7.setText(getData("exercise7", ""))
        binding.etSets7.setText(getData("sets7", ""))
        binding.etReps7.setText(getData("reps7", ""))
        binding.etWeight7.setText(getData("weight7", ""))
        binding.etAchieved7.setText(getData("achieved7", ""))
        binding.etTips7.setText(getData("tips7", ""))
        binding.etExtra7.setText(getData("extra7", ""))

        binding.etExercise8.setText(getData("exercise8", ""))
        binding.etSets8.setText(getData("sets8", ""))
        binding.etReps8.setText(getData("reps8", ""))
        binding.etWeight8.setText(getData("weight8", ""))
        binding.etAchieved8.setText(getData("achieved8", ""))
        binding.etTips8.setText(getData("tips8", ""))
        binding.etExtra8.setText(getData("extra8", ""))

        binding.btnTimer.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutTable2Fragment_to_stopWatchFragment)
        }
        binding.btnHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutTable2Fragment_to_mainMenuFragment)
        }
        binding.btnSaveData.setOnClickListener {
            editor = sf.edit()

            editor.putString("title1",binding.etNameDay2.text.toString())

            editor.putString("exercise1", binding.etExercise1.text.toString())
            editor.putString("sets1", binding.etSets1.text.toString())
            editor.putString("reps1", binding.etReps1.text.toString())
            editor.putString("weight1", binding.etWeight1.text.toString())
            editor.putString("achieved1", binding.etAchieved1.text.toString())
            editor.putString("tips1", binding.etTips1.text.toString())
            editor.putString("extra1", binding.etExtra1.text.toString())

            editor.putString("exercise2", binding.etExercise2.text.toString())
            editor.putString("sets2", binding.etSets2.text.toString())
            editor.putString("reps2", binding.etReps2.text.toString())
            editor.putString("weight2", binding.etWeight2.text.toString())
            editor.putString("achieved2", binding.etAchieved2.text.toString())
            editor.putString("tips2", binding.etTips2.text.toString())
            editor.putString("extra2", binding.etExtra2.text.toString())

            editor.putString("exercise3", binding.etExercise3.text.toString())
            editor.putString("sets3", binding.etSets3.text.toString())
            editor.putString("reps3", binding.etReps3.text.toString())
            editor.putString("weight3", binding.etWeight3.text.toString())
            editor.putString("achieved3", binding.etAchieved3.text.toString())
            editor.putString("tips3", binding.etTips3.text.toString())
            editor.putString("extra3", binding.etExtra3.text.toString())

            editor.putString("exercise4", binding.etExercise4.text.toString())
            editor.putString("sets4", binding.etSets4.text.toString())
            editor.putString("reps4", binding.etReps4.text.toString())
            editor.putString("weight4", binding.etWeight4.text.toString())
            editor.putString("achieved4", binding.etAchieved4.text.toString())
            editor.putString("tips4", binding.etTips4.text.toString())
            editor.putString("extra4", binding.etExtra4.text.toString())

            editor.putString("exercise5", binding.etExercise5.text.toString())
            editor.putString("sets5", binding.etSets5.text.toString())
            editor.putString("reps5", binding.etReps5.text.toString())
            editor.putString("weight5", binding.etWeight5.text.toString())
            editor.putString("achieved5", binding.etAchieved5.text.toString())
            editor.putString("tips5", binding.etTips5.text.toString())
            editor.putString("extra5", binding.etExtra5.text.toString())

            editor.putString("exercise6", binding.etExercise6.text.toString())
            editor.putString("sets6", binding.etSets6.text.toString())
            editor.putString("reps6", binding.etReps6.text.toString())
            editor.putString("weight6", binding.etWeight6.text.toString())
            editor.putString("achieved6", binding.etAchieved6.text.toString())
            editor.putString("tips6", binding.etTips6.text.toString())
            editor.putString("extra6", binding.etExtra6.text.toString())

            editor.putString("exercise7", binding.etExercise7.text.toString())
            editor.putString("sets7", binding.etSets7.text.toString())
            editor.putString("reps7", binding.etReps7.text.toString())
            editor.putString("weight7", binding.etWeight7.text.toString())
            editor.putString("achieved7", binding.etAchieved7.text.toString())
            editor.putString("tips7", binding.etTips7.text.toString())
            editor.putString("extra7", binding.etExtra7.text.toString())

            editor.putString("exercise8", binding.etExercise8.text.toString())
            editor.putString("sets8", binding.etSets8.text.toString())
            editor.putString("reps8", binding.etReps8.text.toString())
            editor.putString("weight8", binding.etWeight8.text.toString())
            editor.putString("achieved8", binding.etAchieved8.text.toString())
            editor.putString("tips8", binding.etTips8.text.toString())
            editor.putString("extra8", binding.etExtra8.text.toString())
            editor.apply()

            Toast.makeText(requireContext(), "Workout saved", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
    //function to retrieve data from SharedPreferences
    private fun getData(key: String, defaultValue: String): String {
        return sf.getString(key, defaultValue) ?: defaultValue
    }

}