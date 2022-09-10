package com.example.wantedpreonboarding.presentation.mapper

import com.example.wantedpreonboarding.presentation.model.TopNews
import com.example.wantedpreonboarding.domain.model.TopNews as TopNewsDomainModel

/**
 * @Created by 김현국 2022/09/07
 */
fun mappingDomainModelToPresentationModel(
    topNewsList: List<TopNewsDomainModel>
): List<TopNews> {
    return topNewsList.map { domainModel ->
        TopNews(
            title = domainModel.title,
            writer = domainModel.author,
            writedTime = domainModel.publishedAt,
            imageUrl = domainModel.imageUrl,
            content = domainModel.content,
            description = domainModel.description
        )
    }
}

fun mappingTopNewsPresentationModelToDomainModel(
    topNews: TopNews
): TopNewsDomainModel {
    return TopNewsDomainModel(
        title = topNews.title,
        author = topNews.writer,
        publishedAt = topNews.writedTime,
        imageUrl = topNews.imageUrl,
        content = topNews.content,
        description = topNews.description)
}
