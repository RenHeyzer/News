package com.example.news.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news.base.BaseDiffUtilCallback
import com.example.news.base.BaseLoading
import com.example.news.databinding.ItemLoadingBinding
import com.example.news.databinding.ItemSourcesBinding
import com.example.news.domain.models.Sources
import com.example.news.utils.Constants
import com.example.news.utils.Constants.ITEM


class SourcesAdapter :
    ListAdapter<Sources, RecyclerView.ViewHolder>(
        BaseDiffUtilCallback<Sources>()
    ), BaseLoading {

    private var isLoading = false

    class SourcesViewHolder(
        private val itemBinding: ItemSourcesBinding,
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(item: Sources) {
            itemBinding.apply {
                itemName.text = item.name
                itemDescription.text = item.description
                itemCountry.text = item.country
                itemCategory.text = item.category
                itemLanguage.text = item.language
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
            SourcesViewHolder(ItemSourcesBinding.inflate(inflater, parent, false))
        } else {
            LoadingViewHolder(ItemLoadingBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ITEM -> {
                val viewHolder: SourcesViewHolder = holder as SourcesViewHolder
                viewHolder.onBind(getItem(position))
            }
            Constants.LOADING -> {
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
            Constants.LOADING
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