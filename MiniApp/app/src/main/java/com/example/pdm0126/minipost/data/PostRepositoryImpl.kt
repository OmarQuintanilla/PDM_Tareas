package com.example.pdm0126.minipost.data

import com.example.pdm0126.minipost.model.Post
import com.example.pdm0126.minipost.model.PostDto
import com.example.pdm0126.minipost.model.toModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostRepositoryImpl(
    private val client: io.ktor.client.HttpClient = KtorClient.client
) : PostRepository {

    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    override suspend fun getPosts(): Result<List<Post>> {
        return try {
            val response: List<PostDto> = client.get("$BASE_URL/posts").body()
            Result.success(response.map { it.toModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createPost(title: String, body: String): Result<Post> {
        return try {
            val request = PostDto(
                userId = 1,
                title = title,
                body = body
            )
            val response: PostDto = client.post("$BASE_URL/posts") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            Result.success(response.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}