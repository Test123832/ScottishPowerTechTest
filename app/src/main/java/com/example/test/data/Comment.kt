package com.example.test.data

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Parcelize
@Serializable
data class Comment(
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "postId")
    val postId: Int,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "email")
    val email: String,
    @SerialName(value = "body")
    val body: String,
) : Parcelable
