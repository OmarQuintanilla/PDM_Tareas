package com.example.pdm0126.minipost.model

fun PostDto.toModel(): Post {
    return Post(
        id = this.id ?: 0,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}

fun Post.toDto(): PostDto {
    return PostDto(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}