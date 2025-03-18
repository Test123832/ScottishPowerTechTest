package com.example.test

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.test.data.Comment
import okhttp3.internal.toImmutableList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeFragment(
    animatedVisibilityScope: AnimatedVisibilityScope,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val commentList by homeViewModel.commentList.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeContent(
            animatedVisibilityScope = animatedVisibilityScope,
            uiState = uiState,
            commentList = commentList.toImmutableList(),
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.HomeContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiState: HomeUiState,
    commentList: List<Comment>,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CommentsScreen(commentList)
    }
}


@Composable
fun CommentsScreen(commentList: List<Comment>) {
    Column {
        commentList.forEach { message ->
            CommentItem(message)
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Name: ${comment.name}", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Email: ${comment.email}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Body: ${comment.body}", style = MaterialTheme.typography.bodySmall)
    }
}