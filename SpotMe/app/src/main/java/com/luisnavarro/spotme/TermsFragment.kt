package com.luisnavarro.spotme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentMainMenuBinding
import com.luisnavarro.spotme.databinding.FragmentTermsBinding


class TermsFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsBinding.inflate(inflater,container,false)
        binding.btnAccept.setOnClickListener {
            it.findNavController().navigate(R.id.action_termsFragment_to_newAccountFragment)
        }
        binding.btnHome.setOnClickListener {
            it.findNavController()?.navigate(R.id.action_termsFragment_to_homeFragment)
        }
        return binding.root
    }


}