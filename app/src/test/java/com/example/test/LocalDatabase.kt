package com.example.test

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.test.database.CommentDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
abstract class LocalDatabase {
    lateinit var db: CommentDatabase

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(
            getApplicationContext(), CommentDatabase
            ::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}
