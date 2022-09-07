package com.example.wantedpreonboarding.data.mapper

import com.example.wantedpreonboarding.domain.model.TopNews
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import com.example.wantedpreonboarding.data.model.TopNews as TopNewsResponse

/**
 * @Created by 김현국 2022/09/07
 */

fun mappingTopNewsResponseToDomainModel(topNews: TopNewsResponse): List<TopNews> {
    return topNews.articles.map { article ->
        TopNews(
            title = article.title,
            author = article.author ?: "",
            imageUrl = article.urlToImage,
            publishedAt = parseResponsePublishTime(article.publishedAt),
            content = article.content
        )
    }
}

fun parseResponsePublishTime(publishedAt: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val date = formatter.parse(publishedAt)?.time
    val currentDateTime = Calendar.getInstance().time

    val diff: Long = date?.minus(currentDateTime.time) ?: 0
    val seconds = diff / 1000
    val minutes = seconds / 60

    return abs((minutes / 60)).toString()
}
