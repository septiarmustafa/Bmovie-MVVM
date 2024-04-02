package com.ansorisan.movieku_kt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ansorisan.movieku_kt.BuildConfig
import com.ansorisan.movieku_kt.databinding.MovieListBinding
import com.ansorisan.movieku_kt.models.Movies
import com.bumptech.glide.Glide

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val movieList = ArrayList<Movies>()

    fun setMovies(list: List<Movies>) {
        this.movieList.clear()
        this.movieList.addAll(list)
    }

    inner class ViewHolder(val binding: MovieListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(movieList[position]) {
                binding.apply {
                    title.text = originalTitle
                    lang.text = originalLanguage
                    releaseDate.text = releaseDate.toString()
                    ratingText.text = voteAverage.toString()
                    ratingBar.rating = voteAverage ?: 0f

                    val uriPoster = "${BuildConfig.PHOTO_BASE_URL}$posterPath"
                    Glide.with(itemView).load(uriPoster).into(poster)
                }
            }
        }
    }
}