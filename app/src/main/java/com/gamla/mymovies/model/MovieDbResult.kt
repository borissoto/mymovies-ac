package com.gamla.mymovies.model

data class MovieDbResult(
    val page: Int,
    val results: List<MovieDB>,
    val total_pages: Int,
    val total_results: Int
)