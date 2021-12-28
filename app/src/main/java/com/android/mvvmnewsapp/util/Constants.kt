package com.android.mvvmnewsapp.util

class Constants {
    companion object {
        var API_KEY: String = System.getenv("APIKEY") ?: "e5aad9fa056c484498053bac0a27ca3f"
        const val BASE_URL = "https://newsapi.org"
        const val SEARCH_NEWS_TIME_DELAY = 500L
    }
}