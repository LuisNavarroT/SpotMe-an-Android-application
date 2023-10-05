package com.luisnavarro.spotme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentWorkoutBinding


class WorkoutFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutBinding.inflate(inflater,container,false)

        binding.btnAdd.setOnClickListener {
            if(binding.btnWorkout1.visibility == View.INVISIBLE){
                binding.btnWorkout1.visibility = View.VISIBLE
            } else if(binding.btnWorkout2.visibility == View.INVISIBLE){
                binding.btnWorkout2.visibility = View.VISIBLE
            }else if(binding.btnWorkout3.visibility == View.INVISIBLE){
                binding.btnWorkout3.visibility = View.VISIBLE
            }else if(binding.btnWorkout4.visibility == View.INVISIBLE){
                binding.btnWorkout4.visibility = View.VISIBLE
            }else if(binding.btnWorkout5.visibility == View.INVISIBLE){
                binding.btnWorkout5.visibility = View.VISIBLE
            }else{
                Toast.makeText(requireContext(), "Only 5 days at the time", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDelete.setOnClickListener {
            if(binding.btnWorkout5.visibility == View.VISIBLE){
                binding.btnWorkout5.visibility = View.INVISIBLE
            } else if(binding.btnWorkout4.visibility == View.VISIBLE){
                binding.btnWorkout4.visibility = View.INVISIBLE
            }else if(binding.btnWorkout3.visibility == View.VISIBLE){
                binding.btnWorkout3.visibility = View.INVISIBLE
            }else if(binding.btnWorkout2.visibility == View.VISIBLE){
                binding.btnWorkout2.visibility = View.INVISIBLE
            }else if(binding.btnWorkout1.visibility == View.VISIBLE){
                binding.btnWorkout1.visibility = View.INVISIBLE
            }else{
                Toast.makeText(requireContext(), "No more days", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnWorkout1.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutFragment_to_workoutTableFragment)
        }
        binding.btnWorkout2.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutFragment_to_workoutTable2Fragment)
        }
        binding.btnWorkout3.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutFragment_to_workoutTable3Fragment)
        }
        binding.btnWorkout4.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutFragment_to_workoutTable4Fragment)
        }
        binding.btnWorkout5.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutFragment_to_workoutTable5Fragment)
        }
        binding.btnHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_workoutFragment_to_mainMenuFragment)
        }
        return binding.root
    }


}