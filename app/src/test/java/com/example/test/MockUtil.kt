package com.example.test

import com.example.test.data.Comment

object MockUtil {
    val mockComments = listOf(
        Comment(id = 1, postId = 101, name = "John Doe", email = "john.doe@example.com", body = "Great post!"),
        Comment(id = 2, postId = 102, name = "Jane Smith", email = "jane.smith@example.com", body = "Very insightful!"),
        Comment(id = 3, postId = 103, name = "Alice Johnson", email = "alice.johnson@example.com", body = "Thanks for sharing!"),
        Comment(id = 4, postId = 104, name = "Bob Brown", email = "bob.brown@example.com", body = "Interesting perspective."),
        Comment(id = 5, postId = 105, name = "Charlie Davis", email = "charlie.davis@example.com", body = "Well written!"),
        Comment(id = 6, postId = 106, name = "Eva Wilson", email = "eva.wilson@example.com", body = "I learned a lot."),
        Comment(id = 7, postId = 107, name = "Frank Miller", email = "frank.miller@example.com", body = "Nice work!"),
        Comment(id = 8, postId = 108, name = "Grace Lee", email = "grace.lee@example.com", body = "Helpful information."),
        Comment(id = 9, postId = 109, name = "Hank Hill", email = "hank.hill@example.com", body = "Keep it up!"),
        Comment(id = 10, postId = 110, name = "Ivy Clark", email = "ivy.clark@example.com", body = "Looking forward to more.")
    )

}