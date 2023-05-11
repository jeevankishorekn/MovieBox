package com.jeevan.moviebox.Fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jeevan.moviebox.Model.Popular.Result
import com.jeevan.moviebox.R


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    lateinit var movieTitle : TextView
    lateinit var movieImage : ImageView
    lateinit var release : TextView
    lateinit var rating : TextView
    lateinit var overview : TextView
    lateinit var ratingText: TextView
    lateinit var releaseText: TextView
    lateinit var overviewText: TextView

    val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    companion object{
        val TAG = "MovieDetailFragment"
    }

    @RequiresApi(33)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.getParcelable("MOVIE_DETAILS",Result::class.java)
        movieTitle = view.findViewById(R.id.movieTitle)
        movieImage = view.findViewById(R.id.movie_image)
        release = view.findViewById(R.id.release_date)
        releaseText = view.findViewById(R.id.release_date_text)
        rating = view.findViewById(R.id.rating)
        ratingText = view.findViewById(R.id.rating_text)
        overview = view.findViewById(R.id.overview)
        overviewText = view.findViewById(R.id.overview_text)

        movieTitle.apply {
            text = item?.title
            setTextColor(Color.WHITE)
            textSize = 24f
        }
        release.text = item?.release_date
        if (release.text == "") releaseText.text = ""
        var ratingVal = item?.vote_average.toString()
        if (ratingVal == "null") {
            rating.text = ""
            ratingText.text = ""
        } else rating.text = ratingVal
        overview.text = item?.overview
        if (overview.text == "") overviewText.text = ""
        val options = RequestOptions.fitCenterTransform()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .encodeFormat(Bitmap.CompressFormat.PNG)
        Glide.with(movieImage).load(IMAGE_URL + item?.poster_path).apply(options).into(movieImage)

    }
}