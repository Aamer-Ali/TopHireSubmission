package com.ali.aamer.tophiresubmission.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.aamer.tophiresubmission.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    val moviesLiveData get() = moviesRepository.moviesLiveData

    fun getMoviesList() {
        viewModelScope.launch {
            moviesRepository.getMovies()
        }
    }

}