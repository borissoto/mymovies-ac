package com.gamla.mymovies.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.gamla.mymovies.R
import com.gamla.mymovies.databinding.ActivityDetailBinding
import com.gamla.mymovies.model.Movie
import com.gamla.mymovies.ui.common.loadUrl


class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    private val presenter = DetailPresenter()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val movie: Movie = requireNotNull(intent.getParcelableExtra(MOVIE))
            presenter.onCreate(this, movie)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateUI(movie: Movie) = with(binding) {
        setSupportActionBar(binding.movieDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movieDetailToolbar.title = movie.title

        val background = movie.backdropPath ?: movie.posterPath
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780/${background}")
        movieDetailSummary.text = movie.overview
        movieDetailInfo.setMovie(movie)
        fab.setOnClickListener {
        }
    }
}