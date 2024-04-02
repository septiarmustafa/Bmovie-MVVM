package com.ansorisan.movieku_kt.viewmodels

import androidx.lifecycle.ViewModel
import com.ansorisan.movieku_kt.repositories.MovieRepository

class MovieViewModel: ViewModel() {
    private val repo: MovieRepository = MovieRepository()


}