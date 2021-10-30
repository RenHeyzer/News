package com.example.news.base

import android.util.Log
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            !isLoading()
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
            isLoading()
            Log.e("anime", isLoading().toString())
            if (dy > 0) {
                if (!isLastPage()) {
                    loadMore()
                } else {
                    Log.e("anime", "More data not found!")
                }
            }
        }
    }

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    abstract fun loadMore()
}