package com.example.test.database.mapper

import com.example.test.data.Comment
import com.example.test.database.CommentEntity

object CommentsEntityMapper : EntityMapper<List<Comment>, List<CommentEntity>> {

    override fun asEntity(domain: List<Comment>): List<CommentEntity> {
        return domain.map { comment ->
            CommentEntity(
                id = comment.id,
                postId = comment.postId,
                name = comment.name,
                email = comment.email,
                body = comment.body
            )
        }
    }

    override fun asDomain(entity: List<CommentEntity>): List<Comment> {
        return entity.map { commentEntity ->
            Comment(
                id = commentEntity.id,
                postId = commentEntity.postId,
                name = commentEntity.name,
                email = commentEntity.email,
                body = commentEntity.body
            )
        }
    }
}

fun List<Comment>.asEntity(): List<CommentEntity> {
    return CommentsEntityMapper.asEntity(this)
}

fun List<CommentEntity>?.asDomain(): List<Comment> {
    return CommentsEntityMapper.asDomain(this.orEmpty())
}


