package com.example.test.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.example.test.data.Comment
import com.example.test.database.CommentDao
import com.example.test.database.CommentEntity
import com.example.test.database.mapper.asDomain
import com.example.test.network.AppClient
import com.example.test.network.AppDispatchers
import com.example.test.network.Dispatcher
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@VisibleForTesting
class DetailsRepositoryImpl @Inject constructor(
    private val appClient: AppClient,
    private val commentDao: CommentDao,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : DetailsRepository {

    @WorkerThread
    override fun fetchCommentDetails(id: Int, onComplete: () -> Unit, onError: (String?) -> Unit): Flow<Comment> =
        flow {
            val comment = commentDao.getComment(id).asDomain()
            emit(comment)

        }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
