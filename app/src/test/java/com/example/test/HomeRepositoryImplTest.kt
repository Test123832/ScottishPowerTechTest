package com.example.test

import app.cash.turbine.test
import com.example.test.MockUtil.mockComments
import com.example.test.data.repository.HomeRepositoryImpl
import com.example.test.database.CommentDao
import com.example.test.database.mapper.asEntity
import com.example.test.network.AppClient
import com.example.test.network.AppService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.never

class HomeRepositoryImplTest {

    private lateinit var repository: HomeRepositoryImpl
    private lateinit var client: AppClient
    private val service: AppService = mock()
    private val commentDao: CommentDao = mock()

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    @Before
    fun setup() {
        client = AppClient(service)
        repository = HomeRepositoryImpl(client, commentDao, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchCommentListFromNetworkTest() = runTest {
        //first it's empty, so it fetches from network, then it returns data after fetch
        whenever(commentDao.getAllCommentsList()).thenReturn(emptyList()).thenReturn(mockComments.asEntity())
        whenever(service.fetchCommentList()).thenReturn(
            ApiResponse.responseOf {
                Response.success(
                    mockComments,
                )
            },
        )

        repository.fetchCommentList(
            onStart = {},
            onComplete = {},
            onError = {},
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val actualItem = awaitItem()[0]
            assertEquals("John Doe", actualItem.name)
            assertEquals("Great post!", actualItem.body)
            awaitComplete()
        }

        verify(commentDao, atLeastOnce()).getAllCommentsList()
        verify(service, atLeastOnce()).fetchCommentList()
        verify(commentDao, atLeastOnce()).insertComments(mockComments.asEntity())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun fetchCommentListFromDatabaseTest() = runTest {
        whenever(commentDao.getAllCommentsList()).thenReturn(mockComments.asEntity())

        repository.fetchCommentList(
            onStart = {},
            onComplete = {},
            onError = {},
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val actualItem = awaitItem()[0]
            assertEquals("John Doe", actualItem.name)
            assertEquals("Great post!", actualItem.body)
            awaitComplete()
        }

        verify(commentDao, atLeastOnce()).getAllCommentsList()
        //never saves as it's already populated
        verify(commentDao, never()).insertComments(mockComments.asEntity())
    }
}