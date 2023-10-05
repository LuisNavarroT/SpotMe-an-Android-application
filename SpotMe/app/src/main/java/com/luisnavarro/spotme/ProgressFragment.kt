package com.luisnavarro.spotme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentProgressBinding
import com.luisnavarro.spotme.databinding.FragmentWorkoutBinding
import com.luisnavarro.spotme.databinding.FragmentWorkoutTableBinding


class ProgressFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProgressBinding.inflate(inflater,container,false)

        binding.btnHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_progressFragment_to_mainMenuFragment)
        }
        return binding.root
    }


}