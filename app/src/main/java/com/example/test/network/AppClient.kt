package com.example.test.network

import com.example.test.data.Comment
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class AppClient @Inject constructor(
    private val commentService: AppService,
) {

    suspend fun fetchCommentList(): ApiResponse<List<Comment>> =
        commentService.fetchCommentList()
}
