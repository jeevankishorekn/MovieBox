package com.jeevan.moviebox.Services

import com.jeevan.moviebox.Model.NowPlaying.NowPlaying
import com.jeevan.moviebox.Model.Popular.Popular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey : String) : Call<NowPlaying>

    @GET("popular")
    fun getPopularMovies(@Query("api_key") apiKey : String) : Call<Popular>

}