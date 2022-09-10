package com.example.wantedpreonboarding.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Created by 김현국 2022/09/07
 */
@Parcelize
data class TopNews(
    val imageUrl: String,
    val writer: String,
    val writedTime: String,
    val title: String,
    val content: String,
    val description: String
) : Parcelable
