package com.example.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test.database.CommentDao
import com.example.test.database.CommentEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentDaoTest: LocalDatabase() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private lateinit var commentDao: CommentDao

    @Before
    fun setUp() {
        commentDao = db.commentDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertAndGetComment() = runBlocking {
        val comment = CommentEntity(id = 1, postId = 10, name = "Test Comment", email = "test@example.com", body = "This is a test comment.")
        commentDao.insertComments(listOf(comment))

        val retrievedComment = commentDao.getComment(1)
        assertEquals(comment, retrievedComment)
    }

    @Test
    fun testGetAllCommentsList() = runBlocking {
        val comments = listOf(
            CommentEntity(id = 1, postId = 10, name = "Comment 1", email = "comment1@example.com", body = "Body 1"),
            CommentEntity(id = 2, postId = 20, name = "Comment 2", email = "comment2@example.com", body = "Body 2")
        )
        commentDao.insertComments(comments)

        val allComments = commentDao.getAllCommentsList()
        assertEquals(comments.sortedBy { it.name }, allComments)
        assertEquals(2, allComments.size)
    }

    @Test
    fun testDeleteAll() = runBlocking {
        val comments = listOf(
            CommentEntity(id = 1, postId = 10, name = "Comment 1", email = "comment1@example.com", body = "Body 1"),
            CommentEntity(id = 2, postId = 20, name = "Comment 2", email = "comment2@example.com", body = "Body 2")
        )
        commentDao.insertComments(comments)
        commentDao.deleteAll()

        val allComments = commentDao.getAllCommentsList()
        assertEquals(emptyList<CommentEntity>(), allComments)
    }
}