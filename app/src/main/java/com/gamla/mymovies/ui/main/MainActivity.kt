package com.gamla.mymovies.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.gamla.mymovies.R
import com.gamla.mymovies.databinding.ActivityMainBinding
import com.gamla.mymovies.model.Movie
import com.gamla.mymovies.model.MoviesRepository
import com.gamla.mymovies.model.RemoteConnection
import com.gamla.mymovies.ui.detail.DetailActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity() {

    private val moviesRepository by lazy { MoviesRepository(this) }

    private val moviesAdapter = MoviesAdapter {
        navigateTo(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = moviesAdapter

        lifecycleScope.launch {
            moviesAdapter.movies = moviesRepository.findPopularMovies().results
        }
    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE, movie)
        startActivity(intent)
    }
}
