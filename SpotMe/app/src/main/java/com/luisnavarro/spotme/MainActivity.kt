package com.luisnavarro.spotme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luisnavarro.spotme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // create a reference variable for View Binding
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Not needed if using view binding
        //setContentView(R.layout.activity_main)

        //Enable view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}