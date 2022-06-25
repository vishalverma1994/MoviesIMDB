package com.moviesimdb.model

data class MoviesListResponse(
    var dates: DateData? = null,
    var page: Int = -1,
    var results: List<MoviesResult>? = null,
    var total_pages: Int = -1,
    var total_results: Long = -1
)
