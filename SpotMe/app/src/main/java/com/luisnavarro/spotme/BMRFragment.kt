package com.luisnavarro.spotme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luisnavarro.spotme.databinding.FragmentBMRBinding
import android.widget.Toast
import androidx.navigation.findNavController
import kotlin.math.roundToInt

class BMRFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentBMRBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBMRBinding.inflate(inflater,container,false)

        binding.btnCalculate.setOnClickListener {
            val age = binding.etAgeValue.text.toString()
            val height = binding.etHeightValue.text.toString()
            val weight = binding.etWeightValue.text.toString()
            var bmr = 0f
            val selectedGenderId = binding.rgGender.checkedRadioButtonId
            val ageValue = age.toIntOrNull()



            if (ageValue == null || ageValue !in 15..80) {
                Toast.makeText(requireContext(), "Please enter a valid age between 15 and 80.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (selectedGenderId == -1) {
                Toast.makeText(requireContext(), "Please select your gender.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (height.isEmpty() ){
                Toast.makeText(requireContext(), "Please enter your height.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (weight.isEmpty() ){
                Toast.makeText(requireContext(), "Please enter your weight.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            if (selectedGenderId == R.id.rbMale){
                bmr = 66.5f + (13.75f * weight.toFloat()) + (5.003f * height.toFloat()) - (6.775f * age.toFloat())
            } else if(selectedGenderId == R.id.rbFemale){
                bmr = 655.1f + (9.563f * weight.toFloat()) + (1.850f * height.toFloat()) - (4.676f * age.toFloat())
            }
            displayResult(bmr)
        }

        binding.btnHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_BMRFragment_to_mainMenuFragment)
        }


        return binding.root


    }

    private fun displayResult(bmr:Float){
        val bmrInt = bmr.roundToInt()
        binding.tvBMRresult.text = bmr.toString()
        binding.cvResult.visibility = View.VISIBLE
    }


}