package com.example.test.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(commentList: List<CommentEntity>)

    @Query("SELECT * FROM CommentEntity WHERE id = :id")
    suspend fun getComment(id: Int): CommentEntity?

    @Query("SELECT * FROM CommentEntity ORDER BY name")
    suspend fun getAllCommentsList(): List<CommentEntity>

    @Query("DELETE FROM CommentEntity")
    suspend fun deleteAll()
}