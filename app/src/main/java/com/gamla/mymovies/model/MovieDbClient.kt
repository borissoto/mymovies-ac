package com.gamla.mymovies.model

import retrofit2.Retrofit

object MovieDbClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

    val service = retrofit.create(TheMovieDbService::class.java)
}