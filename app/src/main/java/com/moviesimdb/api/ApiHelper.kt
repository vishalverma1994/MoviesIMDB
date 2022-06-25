package com.moviesimdb.api

import com.moviesimdb.model.MoviesListResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun fetchMoviesList(
        api_key: String,
        language: String,
        page: Int
    ): Response<MoviesListResponse>
}