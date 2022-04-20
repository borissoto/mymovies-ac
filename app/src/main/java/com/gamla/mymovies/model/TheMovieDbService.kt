package com.gamla.mymovies.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("popular")
    fun listPopularMovies(@Query("apiKey") apiKey: String): Call<MovieDbResult>
}