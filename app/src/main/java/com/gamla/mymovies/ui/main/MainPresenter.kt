package com.gamla.mymovies.ui.main

import android.content.Intent
import com.gamla.mymovies.model.Movie
import com.gamla.mymovies.model.MoviesRepository
import com.gamla.mymovies.ui.detail.DetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val moviesRepository: MoviesRepository,
    private val scope: CoroutineScope
) {
    interface View {
        fun showProgress()
        fun updateData(movies: List<Movie>)
        fun hideProgress()
        fun navigateTo(movie: Movie)
    }

    private var view: View? = null

    fun onCreate(view: View) {
        this.view = view

        scope.launch {
            view.showProgress()
            view.updateData(moviesRepository.findPopularMovies().results)
            view.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie) {
        view?.navigateTo(movie)
    }

    fun onDestroy() {
        this.view = null
    }


}