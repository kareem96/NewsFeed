package com.kareemdev.newsfeed.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.kareemdev.core.domain.model.News
import com.kareemdev.newsfeed.R
import com.kareemdev.newsfeed.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val detailNews = intent.getParcelableExtra<News>(EXTRA_DATA)
        if (detailNews != null) {
            showDetail(detailNews)
        }
    }

    private fun showDetail(detailNews: News) {
        detailNews.let {
            Glide.with(this@DetailActivity)
                .load(detailNews.urlToImage)
                .into(binding.ivDetailImage)
            binding.content.tvAuthor.text = detailNews.author
            binding.content.tvDetailDescription.text = detailNews.description
            binding.content.tvContent.text = detailNews.content
        }
        var statusFavorite = detailNews.isFavorite
        setStatusFavorite(statusFavorite)
        binding.fab.setOnClickListener {
            statusFavorite = !statusFavorite
            detailViewModel.setFavoriteNews(detailNews, statusFavorite)
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}