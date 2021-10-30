package com.example.news.domain.models

import com.example.news.base.IBaseDiffModel

data class Sources(

    val id: String? = null,

    val name: String? = null,

    val description: String? = null,

    override val url: String?,

    val category: String? = null,

    val language: String? = null,

    val country: String? = null,

    ) : IBaseDiffModel