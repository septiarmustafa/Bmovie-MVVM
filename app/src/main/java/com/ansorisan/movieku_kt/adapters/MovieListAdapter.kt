package com.ansorisan.movieku_kt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ansorisan.movieku_kt.BuildConfig
import com.ansorisan.movieku_kt.databinding.MovieListBinding
import com.ansorisan.movieku_kt.models.Genres
import com.ansorisan.movieku_kt.models.Movies
import com.bumptech.glide.Glide

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val movieList = ArrayList<Movies>()
    private val genreList = ArrayList<Genres>()

    fun setMovies(list: List<Movies>) {
        this.movieList.clear()
        this.movieList.addAll(list)
        notifyDataSetChanged()
    }

    fun setGenres(list: List<Genres>) {
        this.genreList.clear()
        this.genreList.addAll(list)
        notifyDataSetChanged()
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
                    releaseDate.text = movieList[position].releaseDate
                    ratingText.text = voteAverage.toString()
                    ratingBar.rating = voteAverage ?: 0f

                    val uriPoster = "${BuildConfig.PHOTO_BASE_URL}$posterPath"
                    Glide.with(itemView).load(uriPoster).into(poster)

                    val map = genreList.associate { it.id to it.name }
                    val genres = StringBuilder()
                    val genresId = ArrayList<Int>()
                    if (genreIds != null) {
                        genresId.addAll(genreIds)
                        for (data in genreIds) {
                            genres.append("${map[data]}, ")
                        }
                    }
                    genre.text = genres.dropLast(2)


                }
            }
        }
    }
}