package com.example.wantedpreonboarding.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteSource(

    @SerializedName("id")
    val id: String? = "",

    @SerializedName("name")
    val name: String?
)
