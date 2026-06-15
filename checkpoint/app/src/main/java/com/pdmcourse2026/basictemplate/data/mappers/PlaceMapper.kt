package com.pdmcourse2026.basictemplate.data.mappers

import com.pdmcourse2026.basictemplate.data.api.dto.PlaceDto
import com.pdmcourse2026.basictemplate.model.Place

fun PlaceDto.toModel(): Place {
    return Place(
        id = this.id,
        imageUrl = this.imageUrl,
        name = this.name,
        votes = this.votes
    )
}