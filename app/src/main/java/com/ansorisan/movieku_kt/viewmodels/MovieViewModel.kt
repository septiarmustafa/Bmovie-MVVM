package com.ansorisan.movieku_kt.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ansorisan.movieku_kt.api.RequestState
import com.ansorisan.movieku_kt.models.MovieResponse
import com.ansorisan.movieku_kt.repositories.MovieRepository
import retrofit2.HttpException

class MovieViewModel: ViewModel() {
    private val repo: MovieRepository = MovieRepository()
    private val page = 1

    fun getPopularMovie(): LiveData<RequestState<MovieResponse?>> = liveData {
        emit(RequestState.Loading)
        try {
            val response = repo.getPopularMovie(page)
            emit(RequestState.Success(response))
        } catch (e: HttpException) {
            e.response()?.errorBody()?.string()?.let { RequestState.Error(it) }?.let{emit(it)}
        }
    }
}