package com.ali.aamer.tophiresubmission.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.aamer.tophiresubmission.api.MoviesApi
import com.ali.aamer.tophiresubmission.utils.Constants.TAG
import com.ali.aamer.tophiresubmission.utils.NetworkResult
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val moviesApi: MoviesApi) {

    private val _moviesLiveData =
        MutableLiveData<NetworkResult<List<com.ali.aamer.tophiresubmission.models.MoviesList.Result>>>()
    val moviesLiveData: LiveData<NetworkResult<List<com.ali.aamer.tophiresubmission.models.MoviesList.Result>>> get() = _moviesLiveData

    suspend fun getMovies() {
        _moviesLiveData.postValue(NetworkResult.Loading())
        val response = moviesApi.getMoviesList()
        val moviesList = response.body()!!.results
        if (response.isSuccessful && response.body() != null) {
            _moviesLiveData.postValue(NetworkResult.Success(moviesList))
        } else {
            _moviesLiveData.postValue(NetworkResult.Failure(message = "Issue while fetching the data"))
        }
    }
}