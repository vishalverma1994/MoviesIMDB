package com.moviesimdb.api

import com.moviesimdb.model.MoviesListResponse
import com.moviesimdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.MOVIES_LIST_API)
    suspend fun fetchMoviesList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesListResponse>
}