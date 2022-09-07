package com.example.wantedpreonboarding.data.model

import com.google.gson.annotations.SerializedName

data class TopNews(

    @SerializedName("articles")
    val articles: List<Article>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)
