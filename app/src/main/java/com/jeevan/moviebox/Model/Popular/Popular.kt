package com.jeevan.moviebox.Model.Popular

import java.io.Serializable

data class Popular(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) : Serializable