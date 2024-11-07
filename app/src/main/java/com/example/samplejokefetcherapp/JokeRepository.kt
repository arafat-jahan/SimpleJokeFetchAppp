package com.example.samplejokefetcherapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class JokeRepository {

    private val _joke = MutableLiveData<Joke>()
    val joke: LiveData<Joke> = _joke

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchJoke() {
        RetrofitInstance.api.getRandomJoke().enqueue(object : Callback<Joke> { // Use Retrofit's Callback
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful) {
                    _joke.value = response.body()  // If successful, set the joke
                } else {
                    _error.value = "Error fetching joke."  // If the response is not successful
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                _error.value = "Failed to fetch joke: ${t.message}"  // On failure, set the error message
            }
        })
    }
}
