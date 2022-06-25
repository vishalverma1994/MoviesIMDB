package com.moviesimdb.repository

import com.moviesimdb.api.ApiHelper
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun fetchMoviesList(
        api_key: String,
        language: String,
        page: Int
    ) = apiHelper.fetchMoviesList(api_key, language, page)

}