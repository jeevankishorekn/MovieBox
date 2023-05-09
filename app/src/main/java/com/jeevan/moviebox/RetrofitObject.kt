package com.jeevan.moviebox

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    val retrofitObject : Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}