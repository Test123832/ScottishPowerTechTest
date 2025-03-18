package com.example.test.network

/**
 * A customized error response.
 *
 * @param code A network response code.
 * @param message A network error message.
 */
data class CommentErrorResponse(
  val code: Int,
  val message: String?,
)
