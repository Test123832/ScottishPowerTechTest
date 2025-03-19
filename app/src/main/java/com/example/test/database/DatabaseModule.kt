package com.example.test.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): CommentDatabase {
        return Room
            .databaseBuilder(application, CommentDatabase::class.java, "Comment.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCommentDao(appDatabase: CommentDatabase): CommentDao {
        return appDatabase.commentDao()
    }

}
