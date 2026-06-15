package com.pdmcourse2026.basictemplate.data.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    @SerialName("id") val id: Int,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("name") val name: String,
    @SerialName("votes") val votes: Int
)

@Serializable
data class ErrorResponseDto(
    @SerialName("ok") val ok: Boolean,
    @SerialName("message") val message: String? = null
)