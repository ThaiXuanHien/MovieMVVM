package com.hienthai.moviemvvm.view.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hienthai.moviemvvm.data.api.TAKE
import com.hienthai.moviemvvm.data.api.TheMovieDBInterface
import com.hienthai.moviemvvm.data.repository.NetworkState
import com.hienthai.moviemvvm.data.repository.PopularMovieDataSource
import com.hienthai.moviemvvm.data.repository.PopularMovieDataSourceFactory
import com.hienthai.moviemvvm.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository(private val apiService: TheMovieDBInterface) {

    private lateinit var moviePagedList: LiveData<PagedList<Movie>>
    private lateinit var movieDataSourceFactory: PopularMovieDataSourceFactory

    fun fetchLivePagedListMovie(compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>> {
        movieDataSourceFactory = PopularMovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(TAKE)
            .build()

        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations
            .switchMap(
                movieDataSourceFactory.popularMoviesLiveDataSource,
                PopularMovieDataSource::networkState)
    }

}