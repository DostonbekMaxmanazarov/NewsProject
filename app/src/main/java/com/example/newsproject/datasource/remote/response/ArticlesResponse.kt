package com.example.newsproject.datasource.remote.response

import kotlinx.serialization.Serializable

class Response<T>(
    val body: T?,
    val status: String?,
    val totalResults: Int?
)

typealias ArticlesResponse = Response<ArticlesResultData>

@Serializable
data class ArticlesResultData(
    val articles: List<ArticleItemResponse>? = null
)

@Serializable
data class ArticleItemResponse(
    val source: SourceResponse? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

@Serializable
data class SourceResponse(
    val id: String? = null, val name: String? = null
)