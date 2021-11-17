package com.hienthai.moviemvvm.data.api

import com.hienthai.moviemvvm.data.vo.MovieDetails
import com.hienthai.moviemvvm.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("movie/popular")
    fun getMoviePopular(@Query("page") page : Int) : Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId : Int) : Single<MovieDetails>
}