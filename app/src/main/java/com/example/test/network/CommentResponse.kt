package com.example.test.network

import com.example.test.data.Comment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentResponse(
  @SerialName(value = "count") val count: Int,
  @SerialName(value = "next") val next: String?,
  @SerialName(value = "previous") val previous: String?,
  @SerialName(value = "results") val results: List<Comment>,
)
