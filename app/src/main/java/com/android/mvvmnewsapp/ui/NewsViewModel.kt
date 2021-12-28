package com.android.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import com.android.mvvmnewsapp.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

}