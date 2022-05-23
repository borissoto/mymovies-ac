package com.gamla.mymovies.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gamla.mymovies.databinding.ActivityDetailBinding
import com.gamla.mymovies.model.Movie
import com.gamla.mymovies.ui.common.loadUrl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {
    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(requireNotNull(intent.getParcelableExtra(MOVIE)))
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { updateUi(it.movie) }
            }
        }
    }

    private fun updateUi(movie: Movie) = with(binding) {
        setSupportActionBar(binding.movieDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movieDetailToolbar.title = movie.title

        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780/${movie.backdropPath}")
        movieDetailSummary.text = movie.overview
        movieDetailInfo.setMovie(movie)
        fab.setOnClickListener {
        }
    }
}