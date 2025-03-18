package com.example.test.data.repository

import androidx.annotation.WorkerThread
import com.example.test.data.Comment
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    @WorkerThread
    fun fetchCommentList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<Comment>>
}
