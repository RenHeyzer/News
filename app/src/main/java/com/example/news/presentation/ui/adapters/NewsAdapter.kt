package com.example.news.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news.base.BaseDiffUtilCallback
import com.example.news.base.BaseLoading
import com.example.news.databinding.ItemEverythingBinding
import com.example.news.databinding.ItemLoadingBinding
import com.example.news.domain.models.Everything
import com.example.news.common.constants.Constants.ITEM
import com.example.news.common.constants.Constants.LOADING
import com.example.news.common.extensions.dateFormatter
import com.example.news.common.extensions.loadImage

class NewsAdapter :
    ListAdapter<Everything, RecyclerView.ViewHolder>(
        BaseDiffUtilCallback<Everything>()
    ), BaseLoading {

    private var isLoading = false

    class NewsViewHolder(
        private val itemBinding: ItemEverythingBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(article: Everything) {
            itemBinding.apply {
                if (article.equals("null")) {
                    itemImage.loadImage(null)
                    published.isVisible = false
                    name.isVisible = false
                    author.isVisible = false
                    itemTitle.isVisible = false
                    itemDescription.isVisible = false
                } else {
                    itemImage.loadImage(article.urlToImage)
                    published.text = dateFormatter(article.publishedAt)
                    name.text = article.source?.name
                    author.text = article.author
                    itemTitle.text = article.title
                    itemDescription.text = article.description
                }
            }
        }
    }

    class LoadingViewHolder(
        private val itemBinding: ItemLoadingBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onLoad() {
            itemBinding.swipeLoading.isVisible = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == ITEM) {
            NewsViewHolder(ItemEverythingBinding.inflate(inflater, parent, false))
        } else {
            LoadingViewHolder(ItemLoadingBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ITEM -> {
                val viewHolder: NewsViewHolder = holder as NewsViewHolder
                viewHolder.onBind(getItem(position))
            }
            LOADING -> {
                val loadingViewHolder: LoadingViewHolder = holder as LoadingViewHolder
                loadingViewHolder.onLoad()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size - 1 && isLoading) {
            LOADING
        } else {
            ITEM
        }
    }

    override fun hideProgressBar() {
        isLoading = false
    }

    override fun showProgressBar() {
        isLoading = true
    }

}
