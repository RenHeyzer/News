package com.example.news.data.network.dtos

import com.example.news.base.IBaseDiffModel
import com.example.news.domain.models.Sources
import com.google.gson.annotations.SerializedName

data class SourcesDto(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("url")
    override val url: String?,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("country")
    val country: String? = null,

    ) : IBaseDiffModel

fun SourcesDto.toSources(): Sources {
    return Sources(
        id,
        name,
        description,
        url,
        category,
        language,
        country,
    )
}
