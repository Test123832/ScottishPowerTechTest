package com.example.test.data.repository

import androidx.annotation.WorkerThread
import com.example.test.data.Comment
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    @WorkerThread
    fun fetchCommentDetails(
        id: Int,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<Comment>
}