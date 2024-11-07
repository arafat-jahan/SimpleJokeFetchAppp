package com.example.samplejokefetcherapp

import retrofit2.Call
import retrofit2.http.GET

interface JokeApiService {
    @GET("random_joke")  // Ensure the correct endpoint is used
    fun getRandomJoke(): Call<Joke>
}
