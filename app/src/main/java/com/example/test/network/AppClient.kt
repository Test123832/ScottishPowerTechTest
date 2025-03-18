package com.example.test.network

import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class AppClient @Inject constructor(
    private val commentService: AppService,
) {

    suspend fun fetchCommentList(): ApiResponse<CommentResponse> =
        commentService.fetchCommentList()
}
