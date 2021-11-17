package com.hienthai.moviemvvm.view.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hienthai.moviemvvm.data.repository.NetworkState
import com.hienthai.moviemvvm.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val repository: MoviePagedListRepository) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val moviePagedList: LiveData<PagedList<Movie>> by lazy {
        repository.fetchLivePagedListMovie(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        repository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}