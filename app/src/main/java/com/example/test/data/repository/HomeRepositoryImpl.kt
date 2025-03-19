package com.example.test.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.example.test.database.CommentDao
import com.example.test.database.mapper.asDomain
import com.example.test.database.mapper.asEntity
import com.example.test.network.AppClient
import com.example.test.network.AppDispatchers
import com.example.test.network.Dispatcher
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@VisibleForTesting
class HomeRepositoryImpl @Inject constructor(
    private val appClient: AppClient,
    private val commentDao: CommentDao,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : HomeRepository {

    @WorkerThread
    override fun fetchCommentList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ) = flow {
        var comments = commentDao.getAllCommentsList().asDomain()
        if (comments.isEmpty()) {
            //fetches a list of [Comment] from the network and getting [ApiResponse] asynchronously.
            val response = appClient.fetchCommentList()
            response.suspendOnSuccess {
                comments = data
                commentDao.insertComments(comments.asEntity())
                emit(commentDao.getAllCommentsList().asDomain())
            }.onFailure { // handles the all error cases from the API request fails.
                onError(message())
            }
        } else {
            emit(commentDao.getAllCommentsList().asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
