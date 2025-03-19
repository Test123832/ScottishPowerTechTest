package com.example.test.network

import com.example.test.data.Comment
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface AppService {

    @GET("comments")
    suspend fun fetchCommentList(
    ): ApiResponse<List<Comment>>
}
