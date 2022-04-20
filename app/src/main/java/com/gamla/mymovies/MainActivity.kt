package com.gamla.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.gamla.mymovies.databinding.ActivityMainBinding
import com.gamla.mymovies.model.MovieDbClient
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = MoviesAdapter(
            listOf(
                Movie("Titulo1", "https://loremflickr.com/320/240?lock=1"),
                Movie("Titulo2", "https://loremflickr.com/320/240?lock=2"),
                Movie("Titulo3", "https://loremflickr.com/320/240?lock=3"),
                Movie("Titulo4", "https://loremflickr.com/320/240?lock=4"),
                Movie("Titulo5", "https://loremflickr.com/320/240?lock=5"),
                Movie("Titulo6", "https://loremflickr.com/320/240?lock=6"),
            )
        ) { movie ->
            Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_LONG).show()
        }

        thread {
            MovieDbClient.service.listPopularMovies()
        }
    }
}