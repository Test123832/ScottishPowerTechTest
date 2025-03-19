package com.example.test.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.test.data.Comment
import com.example.test.design.AppColors
import com.example.test.design.AppSize
import okhttp3.internal.toImmutableList

@Composable
fun HomeFragment(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val commentList by homeViewModel.commentList.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeContent(
            navController,
            commentList = commentList.toImmutableList(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    navController: NavController,
    commentList: List<Comment>,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Homepage")
                }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColors.lightGrey)
                .padding(innerPadding)
        ) {
            CommentsScreen(commentList, navController)
        }
    }

}


@Composable
fun CommentsScreen(commentList: List<Comment>, navController: NavController) {
    LazyColumn(modifier = Modifier.padding(26.dp)) {
        items(commentList) { item ->
            CommentItem(item, navController)
        }
    }
}

@Composable
fun CommentItem(comment: Comment, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(15.dp))
            .clickable {
                navController.navigate("details/${comment.id}")
            }
    ) {
        Text(
            text = comment.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(AppSize.small, AppSize.small, AppSize.small, AppSize.xsmall)
        )
        Text(
            text = comment.body,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(AppSize.small, AppSize.xsmall, AppSize.small, AppSize.small)
        )
    }
}