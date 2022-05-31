package com.gamla.mymovies.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gamla.mymovies.R
import com.gamla.mymovies.databinding.FragmentMainBinding
import com.gamla.mymovies.model.Movie
import com.gamla.mymovies.model.MoviesRepository
import com.gamla.mymovies.ui.common.visible
import com.gamla.mymovies.ui.detail.DetailFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(MoviesRepository(requireActivity() as AppCompatActivity))
    }

    private val moviesAdapter = MoviesAdapter { viewModel.onMovieClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = moviesAdapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { binding.updateUI(it) }
            }
        }
    }

    private fun FragmentMainBinding.updateUI(state: MainViewModel.UiState) {
        progressBar.visible = state.loading
        state.movies?.let(moviesAdapter::submitList)
        state.navigateTo?.let(::navigateTo)
    }

    private fun navigateTo(movie: Movie) {
        val navAction = MainFragmentDirections.actionMainToDetail(movie)
        findNavController().navigate(navAction)
//        findNavController().navigate(
//            R.id.action_main_to_detail,
//            bundleOf(DetailFragment.MOVIE to movie)
//        )
    }
}