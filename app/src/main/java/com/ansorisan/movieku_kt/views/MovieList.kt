package com.ansorisan.movieku_kt.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ansorisan.movieku_kt.adapters.MovieListAdapter
import com.ansorisan.movieku_kt.api.RequestState
import com.ansorisan.movieku_kt.databinding.ActivityMovieListBinding
import com.ansorisan.movieku_kt.viewmodels.MovieViewModel

class MovieList : AppCompatActivity() {
    private var _binding: ActivityMovieListBinding? = null
    private val binding get() = _binding!!
    private var adapter: MovieListAdapter? = null
    private var layoutManager: LayoutManager? = null
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestThenObserveAnyChangeGenres()
        requestThenObserveAnyChangePopularMovie()
        setupRecyclerView()
    }

    private fun requestThenObserveAnyChangePopularMovie(){
        viewModel.getPopularMovie().observe(this){
            if (it != null) {
                when(it) {
                    is RequestState.Loading -> {showLoading()}
                    is RequestState.Success -> {
                        hideLoading()
                        it.data?.results?.let { data -> adapter?.setMovies(data) }
                    }
                    is RequestState.Error -> {
                        hideLoading()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun requestThenObserveAnyChangeGenres(){
        viewModel.getGenres().observe(this){
            if (it != null) {
                when(it) {
                    is RequestState.Loading -> {}
                    is RequestState.Success -> it.data?.genres?.let { data -> adapter?.setGenres(data) }
                    is RequestState.Error -> Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MovieListAdapter()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            movieList.adapter = adapter
            movieList.layoutManager = layoutManager
        }
    }

    private fun showLoading(){
        binding.loading.show()
    }
    private fun hideLoading(){
        binding.loading.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}