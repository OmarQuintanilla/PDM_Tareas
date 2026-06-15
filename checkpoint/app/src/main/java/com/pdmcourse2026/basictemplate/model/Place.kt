package com.pdmcourse2026.basictemplate.model

data class Place(
    val id: Int = 0,
    val imageUrl: String,
    val name: String,
    val votes: Int =0
)