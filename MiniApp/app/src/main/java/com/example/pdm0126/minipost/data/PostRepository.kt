package com.example.pdm0126.minipost.data

import com.example.pdm0126.minipost.model.Post

interface PostRepository {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun createPost(title: String, body: String): Result<Post>
}