package com.ansorisan.movieku_kt.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GenreResponse(

	@field:SerializedName("genres")
	val genres: List<Genres>? = null
) : Parcelable