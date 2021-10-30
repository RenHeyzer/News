package com.example.news.domain.models

import com.example.news.base.IBaseDiffModel

data class Everything(

    val source: Source? = null,

    val author: String? = null,

    val title: String? = null,

    val description: String? = null,

    override val url: String?,

    val urlToImage: String? = null,

    val publishedAt: String? = null,

    val content: String? = null

) : IBaseDiffModel

data class Source(

    val id: String? = null,

    val name: String? = null
)
