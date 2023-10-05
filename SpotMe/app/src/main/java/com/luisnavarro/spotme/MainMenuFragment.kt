package com.luisnavarro.spotme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater,container,false)

        binding.btnWorkouts.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuFragment_to_workoutFragment)
        }
        binding.btnBMR.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuFragment_to_BMRFragment)
        }
        binding.btnTrack.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuFragment_to_progressFragment)
        }
        binding.btnEdit.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuFragment_to_editFragment)
        }
        return binding.root
    }

}