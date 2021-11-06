package com.strukov.api.api

import com.strukov.api.data.dto.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface FakeApi {
    @GET("/posts")
    suspend fun getPosts(): Response<List<PostDto>>
}