package com.jeevan.moviebox.Listeners

import com.jeevan.moviebox.Model.Popular.Result

interface MainFragmentListener {
    fun getMoviesById(result: Result)
}