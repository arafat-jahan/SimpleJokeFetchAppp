package com.example.samplejokefetcherapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplejokefetcherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: JokeViewModel by viewModels()
    private lateinit var jokeAdapter: JokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter with an empty list
        jokeAdapter = JokeAdapter(emptyList())

        // Set up RecyclerView with adapter and layout manager
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = jokeAdapter
        }

        // Set up button click to fetch a new joke
        binding.buttonGetJoke.setOnClickListener {
            viewModel.getNewJoke()
        }

        // Observe joke LiveData from ViewModel
        viewModel.joke.observe(this) { joke ->
            joke?.let { jokeAdapter.updateJokes(listOf(it)) }
        }

        // Observe error LiveData from ViewModel
        viewModel.error.observe(this) { errorMsg ->
            errorMsg?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }
    }
}