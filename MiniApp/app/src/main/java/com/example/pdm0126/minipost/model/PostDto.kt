package com.example.pdm0126.minipost.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("userId")
    val userId: Int,

    @SerialName("title")
    val title: String,

    @SerialName("body")
    val body: String
)