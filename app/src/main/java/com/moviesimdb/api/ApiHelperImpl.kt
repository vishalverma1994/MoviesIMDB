package com.moviesimdb.api

import com.moviesimdb.model.MoviesListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchMoviesList(
        api_key: String,
        language: String,
        page: Int
    ): Response<MoviesListResponse> =
        apiService.fetchMoviesList(api_key, language, page)

}