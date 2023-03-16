package com.ali.aamer.tophiresubmission.api

import com.ali.aamer.tophiresubmission.models.MoviesList.Movies
import com.ali.aamer.tophiresubmission.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("now_playing/?")
    suspend fun getMoviesList(@Query("api_key") zipCode: String = API_KEY): Response<Movies>
}
