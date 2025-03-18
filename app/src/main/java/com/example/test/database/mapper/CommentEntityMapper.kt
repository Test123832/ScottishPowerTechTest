package com.example.test.database.mapper

import com.example.test.data.Comment
import com.example.test.database.CommentEntity

object CommentEntityMapper : EntityMapper<Comment, CommentEntity> {

    override fun asEntity(domain: Comment): CommentEntity {
        return CommentEntity(
            id = domain.id,
            postId = domain.postId,
            name = domain.name,
            email = domain.email,
            body = domain.body
        )
    }

    override fun asDomain(entity: CommentEntity): Comment {
        return Comment(
            id = entity.id,
            postId = entity.postId,
            name = entity.name,
            email = entity.email,
            body = entity.body
        )
    }
}

fun Comment.asEntity(): CommentEntity {
    return CommentEntityMapper.asEntity(this)
}

fun CommentEntity.asDomain(): Comment {
    return CommentEntityMapper.asDomain(this)
}

