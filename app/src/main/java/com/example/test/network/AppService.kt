package com.example.test.network

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface AppService {

    @GET("comment")
    suspend fun fetchCommentList(
    ): ApiResponse<CommentResponse>
}
