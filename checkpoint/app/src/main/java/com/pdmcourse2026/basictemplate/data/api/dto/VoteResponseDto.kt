package com.pdmcourse2026.basictemplate.data.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoteResponseDto(
    @SerialName("ok") val ok: Boolean,
    @SerialName("message") val message: String? = null
)