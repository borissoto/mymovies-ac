package com.gamla.mymovies.ui.detail

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.movieDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(MOVIE)

        if (movie != null) {
            binding.movieDetailToolbar.title = movie.title

            val background = movie.backdropPath ?: movie.posterPath

            binding.movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780/${background}")
            binding.movieDetailSummary.text = movie.overview
            bindDetailInfo(binding.movieDetailInfo, movie)
            binding.fab.setOnClickListener{

            }
        }
    }

    private fun bindDetailInfo(detailInfo: TextView, movie: Movie) {
        detailInfo.text = buildSpannedString {
            appendInfo(R.string.original_language, movie.originalLanguage)
            appendInfo(R.string.original_title, movie.originalTitle)
            appendInfo(R.string.release_date, movie.releaseDate)
            appendInfo(R.string.popularity, movie.popularity.toString())
            appendInfo(R.string.vote_average, movie.voteAveraqe.toString())
        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String){
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }
}