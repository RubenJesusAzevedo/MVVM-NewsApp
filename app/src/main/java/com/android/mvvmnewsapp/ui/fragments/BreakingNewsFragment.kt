package com.android.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvvmnewsapp.R
import com.android.mvvmnewsapp.adapters.NewsAdapter
import com.android.mvvmnewsapp.ui.NewsActivity
import com.android.mvvmnewsapp.ui.NewsViewModel
import com.android.mvvmnewsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment :  Fragment(R.layout.fragment_breaking_news) {

    lateinit var  viewModel: NewsViewModel
    lateinit var  newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBas()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBas()
                    response.message?.let { message ->
                        Log.e(TAG, "An error Accured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBas()
                }
            }
        })
    }

    private fun hideProgressBas() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBas() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}