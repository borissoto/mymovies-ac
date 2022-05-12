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


class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityDetailBinding.inflate(layoutInflater).run {
            setContentView(root)
            setSupportActionBar(movieDetailToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val movie = intent.getParcelableExtra<Movie>(MOVIE) ?: throw IllegalStateException()

            movieDetailToolbar.title = movie.title

            val background = movie.backdropPath ?: movie.posterPath
            movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780/${background}")
            movieDetailSummary.text = movie.overview
            movieDetailInfo.setMovie(movie)
            fab.setOnClickListener {

            }

        }
    }

}