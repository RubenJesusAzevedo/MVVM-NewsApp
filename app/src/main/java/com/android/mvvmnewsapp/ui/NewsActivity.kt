package com.android.mvvmnewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.mvvmnewsapp.R
import com.android.mvvmnewsapp.db.ArticleDatabase
import com.android.mvvmnewsapp.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }
}
