package com.hienthai.moviemvvm.view.single_movie_details

import androidx.lifecycle.LiveData
import com.hienthai.moviemvvm.data.api.TheMovieDBInterface
import com.hienthai.moviemvvm.data.repository.MovieDetailNetworkDatasource
import com.hienthai.moviemvvm.data.repository.NetworkState
import com.hienthai.moviemvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {
    lateinit var movieDetailNetworkDatasource: MovieDetailNetworkDatasource

    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MovieDetails> {
        movieDetailNetworkDatasource = MovieDetailNetworkDatasource(apiService, compositeDisposable)
        movieDetailNetworkDatasource.fetchMovieDetails(movieId)
        return movieDetailNetworkDatasource.downloadedMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailNetworkDatasource.netWorkState
    }
}