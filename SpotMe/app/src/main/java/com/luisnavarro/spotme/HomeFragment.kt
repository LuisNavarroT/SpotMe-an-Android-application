package com.luisnavarro.spotme

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.luisnavarro.spotme.databinding.FragmentHomeBinding
import com.luisnavarro.spotme.db.User
import com.luisnavarro.spotme.db.UserDao
import com.luisnavarro.spotme.db.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var dao: UserDao
    private lateinit var emailSP: EditText
    private lateinit var sf:SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        emailSP = binding.etEmail
        sf = requireContext().getSharedPreferences("my_sfHome", AppCompatActivity.MODE_PRIVATE)
        editor = sf.edit()
        dao = UserDataBase.getInstance(requireActivity().application).userDao()
        val factory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.btnNewAccount.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
        }
        return binding.root
    }

    private fun loginUser() {


        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()



        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_LONG).show()
        }else if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Password is required", Toast.LENGTH_LONG).show()
        }else{

            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val user = dao.getUserByEmail(email)

                if (user != null){
                    if (user.password == password){
                        // Passwords match, user login is successful
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                            view?.findNavController()?.navigate(R.id.action_homeFragment_to_mainMenuFragment)
                        }
                    }else{
                        // Passwords don't match
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "Invalid password", Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    // User not found in the database
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
                    }
                }

            }

        }
    }

    override fun onPause() {
        super.onPause()
        val email = emailSP.text.toString()
        editor.apply{
            putString("sf_email",email)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val email = sf.getString("sf_email",null)
        emailSP.setText(email)
    }
}