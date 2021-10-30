package com.example.news.data.network.dtos

import com.example.news.base.IBaseDiffModel
import com.example.news.domain.models.Everything
import com.example.news.domain.models.Source
import com.google.gson.annotations.SerializedName

data class EverythingDto(
    @SerializedName("source")
    val source: SourceDto? = null,

    @SerializedName("author")
    val author: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("url")
    override val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @SerializedName("publishedAt")
    val publishedAt: String? = null,

    @SerializedName("content")
    val content: String? = null,

    ) : IBaseDiffModel

fun EverythingDto.toEverything(): Everything {
    return Everything(
        source?.toSource(),
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt,
        content,
    )
}

data class SourceDto(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null
)

fun SourceDto.toSource(): Source {
    return Source(
        id,
        name
    )
}