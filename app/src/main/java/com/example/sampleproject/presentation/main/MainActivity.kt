package com.example.sampleproject.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sampleproject.R
import com.example.sampleproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)



        viewModel.testString.observe(this, Observer { newText ->
            binding.test.text = newText.toString()
        })
    }
}