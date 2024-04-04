package com.ansorisan.movieku_kt.repositories

import com.ansorisan.movieku_kt.BuildConfig
import com.ansorisan.movieku_kt.api.ApiConfig

class MovieRepository {
    private val client = ApiConfig.getApiService()

    suspend fun getPopularMovie(page: Int) = client.getPopularMovie(BuildConfig.API_KEY, page)
    suspend fun getGenres() = client.getGenres(BuildConfig.API_KEY)
}