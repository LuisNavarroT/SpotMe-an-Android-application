package com.luisnavarro.spotme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentNewAccountBinding
import com.luisnavarro.spotme.db.User
import com.luisnavarro.spotme.db.UserDataBase

class NewAccountFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentNewAccountBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewAccountBinding.inflate(inflater,container,false)

        val dao = UserDataBase.getInstance(requireActivity().application).userDao()
        val factory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

        binding.btnCreate.setOnClickListener {
            saveUserData()
        }

        binding.btnHome.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_newAccountFragment_to_homeFragment)
        }

        return binding.root
    }

    private fun saveUserData(){

        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPasswordNewAccount.text.toString()
        val password2 = binding.etPasswordNewAccount2.text.toString()
        val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

        if (email.isEmpty()){
            Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_LONG).show()
        }else if (!email.matches(pattern)){
            Toast.makeText(requireContext(), "Email must use the format: name@provider.domain.", Toast.LENGTH_LONG).show()
        }else{
            if(password.isEmpty() || password2.isEmpty() ){
                Toast.makeText(requireContext(), "Both password fields are required.", Toast.LENGTH_LONG).show()
            }else if (password==password2){
                Toast.makeText(requireContext(), "User created", Toast.LENGTH_LONG).show()
                val user = User(email, password)
                viewModel.insertUser(user)
                clearInput()
                view?.findNavController()?.navigate(R.id.action_newAccountFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(), "Both password fields must match.", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun clearInput(){
        binding.etEmailAddress.setText("")
        binding.etPasswordNewAccount.setText("")
        binding.etPasswordNewAccount2.setText("")
    }

}