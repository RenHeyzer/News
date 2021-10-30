package com.example.news.common.extensions

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.news.base.PaginationScrollListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

var isLoading = false
var isLastPage = false
var page = 1

fun ImageView.loadImage(img: String?) {
    load(img) {
        crossfade(true)
        crossfade(1000)
    }
}

fun Context.toast(mass: String) {
    Toast.makeText(this, mass, Toast.LENGTH_LONG).show()
}

fun RecyclerView.checkLastPage(method: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                method()
            }
        }
    })
}

fun RecyclerView.scrollPagination(getData: () -> Unit) {
    this.addOnScrollListener(object : PaginationScrollListener() {
        override fun isLastPage(): Boolean {
            return isLastPage
        }

        override fun isLoading(): Boolean {
            return isLoading
        }

        override fun loadMore() {
            getData()
        }

    })
}

fun dateFormatter(oldStringDate: String?): String? {
    if (oldStringDate == null || oldStringDate == "")
        return ""
    val newDate: String?
    val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale.ENGLISH)
    newDate = try {
        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldStringDate
    }
    return newDate
}


