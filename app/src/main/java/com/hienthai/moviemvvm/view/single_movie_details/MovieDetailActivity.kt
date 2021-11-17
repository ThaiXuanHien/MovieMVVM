package com.hienthai.moviemvvm.view.single_movie_details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.hienthai.moviemvvm.R
import com.hienthai.moviemvvm.data.api.POSTER_BASE_URL
import com.hienthai.moviemvvm.data.api.TheMovieDBClient
import com.hienthai.moviemvvm.data.api.TheMovieDBInterface
import com.hienthai.moviemvvm.data.repository.NetworkState
import com.hienthai.moviemvvm.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.text.NumberFormat
import java.util.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var repository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra("movieId",1)

        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
        repository = MovieDetailsRepository(apiService)
        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })

    }

    private fun bindUI(it: MovieDetails) {
        txt_movie_title.text = it.title
        txt_movie_tagline.text = it.tagline
        txt_movie_release_date.text = it.releaseDate
        txt_movie_rating.text = it.rating.toString()
        txt_movie_runtime.text = it.runtime.toString() + " minutes"
        txt_movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        txt_movie_budget.text = formatCurrency.format(it.budget)
        txt_movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(img_movie_poster);

    }

    private fun getViewModel(movieId: Int): SingleMovieViewModel {

        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(repository, movieId) as T
            }

        })[SingleMovieViewModel::class.java]

    }
}