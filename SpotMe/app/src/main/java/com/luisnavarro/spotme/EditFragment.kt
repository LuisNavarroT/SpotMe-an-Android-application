package com.luisnavarro.spotme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentEditBinding
import com.luisnavarro.spotme.db.User
import com.luisnavarro.spotme.db.UserDao
import com.luisnavarro.spotme.db.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragment : Fragment() {
    // create a reference variable for View Binding
    private lateinit var binding: FragmentEditBinding

    private lateinit var viewModel: UserViewModel
    private lateinit var dao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater, container, false)

        dao = UserDataBase.getInstance(requireActivity().application).userDao()
        val factory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.btnSave.setOnClickListener {
            editUserData()
        }

        binding.btnDelete.setOnClickListener {
            deleteUserData()
        }

        binding.btnHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_editFragment_to_mainMenuFragment)
        }

        return binding.root
    }

    private fun deleteUserData() {
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPasswordNewAccount.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_LONG).show()
        }else if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Password is required", Toast.LENGTH_LONG).show()
        }else {
             viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                // Check if the user with the given email exists in the database
                val user = dao.getUserByEmail(email)

                if (user != null) {
                    if (user.password == password) {
                        // User exists, delete it
                        viewModel.deleteUser(user)
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "User deleted correctly", Toast.LENGTH_LONG).show()
                            view?.findNavController()?.navigate(R.id.action_editFragment_to_homeFragment)
                        }
                    }else {
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    // User not found
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
    }
    private fun editUserData() {
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPasswordNewAccount.text.toString()
        val newEmail = binding.etNewEmailAddress.text.toString()
        val newPassword = binding.etPasswordNewAccount2.text.toString()

        if (newEmail.isEmpty()) {
            Toast.makeText(requireContext(), "New email is required", Toast.LENGTH_LONG).show()
        } else if (newPassword.isEmpty()) {
            Toast.makeText(requireContext(), "New password is required", Toast.LENGTH_LONG).show()
        } else if (email.isEmpty()) {
            Toast.makeText(requireContext(), "current email is required", Toast.LENGTH_LONG).show()
        } else if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Current password is required", Toast.LENGTH_LONG).show()
        } else {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                // Check if the user with the given email exists in the database
                val user = dao.getUserByEmail(email)

                if (user != null) {
                    if (user.password == password) {
                        // Update user's email and password
                        val newUser = User(newEmail, newPassword)
                        viewModel.insertUser(newUser)
                        val user = User(email, password)
                        viewModel.deleteUser(user)

                        requireActivity().runOnUiThread {
                            Toast.makeText(
                                requireContext(),
                                "User edited correctly",
                                Toast.LENGTH_LONG
                            ).show()
                            view?.findNavController()
                                ?.navigate(R.id.action_editFragment_to_homeFragment)
                        }
                    }else {
                        // Incorrect password
                        requireActivity().runOnUiThread {
                            Toast.makeText(
                                requireContext(),
                                "Incorrect password",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    // User not found
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

