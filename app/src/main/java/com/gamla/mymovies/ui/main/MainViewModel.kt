package com.gamla.mymovies.ui.main

import androidx.lifecycle.*
import com.gamla.mymovies.model.Movie
import com.gamla.mymovies.model.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(movies = moviesRepository.findPopularMovies().results)
            _state.value = _state.value?.copy(loading = false)
        }
    }

    fun onMovieClicked(movie: Movie) {
        _state.value = _state.value.copy(navigateTo = movie)
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie>? = null,
        val navigateTo: Movie? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(moviesRepository) as T
    }
}