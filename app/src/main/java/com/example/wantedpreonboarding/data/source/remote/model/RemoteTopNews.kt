package com.example.wantedpreonboarding.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteTopNews(

    @SerializedName("articles")
    val articles: List<RemoteArticle>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)
