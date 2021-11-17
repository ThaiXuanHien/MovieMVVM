package com.hienthai.moviemvvm.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hienthai.moviemvvm.data.api.TheMovieDBInterface
import com.hienthai.moviemvvm.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class PopularMovieDataSourceFactory(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val popularMoviesLiveDataSource = MutableLiveData<PopularMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val popularMovieDataSource = PopularMovieDataSource(apiService, compositeDisposable)

        popularMoviesLiveDataSource.postValue(popularMovieDataSource)
        return popularMovieDataSource
    }
}