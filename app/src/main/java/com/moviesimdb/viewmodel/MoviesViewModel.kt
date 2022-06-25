package com.moviesimdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviesimdb.model.MoviesListResponse
import com.moviesimdb.repository.MoviesRepository
import com.moviesimdb.utils.Constants
import com.moviesimdb.utils.NetworkHelper
import com.moviesimdb.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _moviesListLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val moviesListLiveData: LiveData<Resource<MoviesListResponse>> get() = _moviesListLiveData

    fun fetchMoviesList(pageNumber: Int) = viewModelScope.launch {
        _moviesListLiveData.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            repository.fetchMoviesList(Constants.API_KEY, Constants.DEFAULT_LANGUAGE, pageNumber).let { response ->
                if (response.isSuccessful) {
                    response.body()?.let { moviesListResponse ->
                        _moviesListLiveData.postValue(Resource.success(moviesListResponse))
                    }
                } else _moviesListLiveData.postValue(Resource.error(response.errorBody().toString(), null))
            }
        } else _moviesListLiveData.postValue(Resource.error(Constants.INTERNET_CONNECTION_ERROR_MESSAGE, null))
    }
}