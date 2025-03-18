package com.example.test.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CommentEntity::class],
    version = 4,
    exportSchema = true,
)
abstract class CommentDatabase : RoomDatabase() {
    abstract fun commentDao(): CommentDao
}
