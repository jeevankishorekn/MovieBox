package com.jeevan.moviebox.Fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.Slide
import com.jeevan.moviebox.Adapters.NPRecyclerAdapter
import com.jeevan.moviebox.Adapters.PRecyclerAdapter
import com.jeevan.moviebox.Listeners.MainFragmentListener
import com.jeevan.moviebox.Model.NowPlaying.NowPlaying
import com.jeevan.moviebox.Model.NowPlaying.Result
import com.jeevan.moviebox.Model.Popular.Popular
import com.jeevan.moviebox.R
import com.jeevan.moviebox.RetrofitObject
import com.jeevan.moviebox.Services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(R.layout.fragment_main), MainFragmentListener {

    private val API_KEY = "672c304500d0e80692cc7ff3a1594b2c"

    lateinit var NPRecyclerView : RecyclerView
    lateinit var NPViewAdapter : NPRecyclerAdapter
    lateinit var PRecyclerView : RecyclerView
    lateinit var PViewAdapter : PRecyclerAdapter
    lateinit var ctx: Context
    override fun onAttach(context: Context) {
        ctx = context
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NPRecyclerView = view.findViewById(R.id.nowPlayingView)
        PRecyclerView = view.findViewById(R.id.popularView)
        val nowPlayingResult = getNowPlayingMovies()
        Log.d(TAG, "onViewCreated: ${nowPlayingResult.size}")
        NPViewAdapter = NPRecyclerAdapter(nowPlayingResult)
        NPRecyclerView.adapter = NPViewAdapter
        NPRecyclerView.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, true)

        getPopularMovies()
        PViewAdapter = PRecyclerAdapter(mutableListOf(),this)
        PRecyclerView.adapter = PViewAdapter
        PRecyclerView.layoutManager = LinearLayoutManager(ctx)


    }

    private fun getPopularMovies() {
        val call = RetrofitObject.retrofitObject.create(RetrofitService::class.java)
            .getPopularMovies(API_KEY)

        val result = mutableListOf<com.jeevan.moviebox.Model.Popular.Result>()
        call.enqueue(object : Callback<Popular> {
            override fun onResponse(
                call: Call<Popular>,
                response: Response<Popular>
            ) {
                if(response.isSuccessful && response.body() != null){
                    Log.d(TAG, "onResponse: Response Successful")
                    for( item in response.body()!!.results)
                        result.add(item)
                    Log.d(TAG, "onResponse: ${result.size}")
                    PViewAdapter = PRecyclerAdapter(result,this@MainFragment)
                    PRecyclerView.adapter = PViewAdapter

                }
            }

            override fun onFailure(call: Call<Popular>, t: Throwable) {
                Log.d(TAG, "onFailure: {${t.message}")
            }
        })
    }

    private fun getNowPlayingMovies(): MutableList<Result> {
        val call = RetrofitObject.retrofitObject.create(RetrofitService::class.java)
            .getNowPlayingMovies(API_KEY)

        val result = mutableListOf<Result>()
        call.enqueue(object : Callback<NowPlaying> {
            override fun onResponse(
                call: Call<NowPlaying>,
                response: Response<NowPlaying>
            ) {
                if(response.isSuccessful && response.body() != null){
                    Log.d(TAG, "onResponse: Response Successful")
                    for( item in response.body()!!.results)
                        result.add(item)
                    Log.d(TAG, "onResponse: ${result.size}")
                    NPViewAdapter = NPRecyclerAdapter(result)
                    NPRecyclerView.adapter = NPViewAdapter

                }
            }

            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                Log.d(TAG, "onFailure: {${t.message}")
            }
        })
        return result
    }

    override fun getMoviesById(result: com.jeevan.moviebox.Model.Popular.Result) {
        requireActivity().supportFragmentManager.apply {
            val bundle = Bundle()
            bundle.putParcelable("MOVIE_DETAILS",result)
            val movieFrag = MovieDetailFragment()
            movieFrag.arguments=bundle
            if(findFragmentById(R.id.movieDetailFragment)?.isVisible == false) {
                beginTransaction()
                    .replace(R.id.fragmentContainerView, movieFrag)
                    .addToBackStack(null)
                    .commit()
            }
            else{
                beginTransaction()
                    .replace(R.id.fragmentContainerView2, movieFrag)
                    .addToBackStack(null)
                    .commit()
            }

        }
    }
}