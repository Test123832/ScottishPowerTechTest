package com.example.test.feature.details

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test.data.Comment
import com.example.test.design.AppColors
import com.example.test.design.AppSize

@Composable
fun DetailsFragment(
    navController: NavController,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
    val comment by detailsViewModel.comment.collectAsStateWithLifecycle()
    comment?.let {
        DetailsView(it, navController)
    } ?: run {
        Text(text = "Post not found")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsView(comment: Comment, navController: NavController) {
    Scaffold(
        containerColor = AppColors.lightGrey,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Comment Details",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp, innerPadding.calculateTopPadding() + 20.dp)
        )
        {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(15.dp))
            ) {
                Text(
                    text = comment.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(
                        AppSize.small,
                        AppSize.small,
                        AppSize.small,
                        AppSize.xsmall
                    )
                )
                Text(
                    text = comment.body,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(AppSize.small, 0.dp, AppSize.small, AppSize.xsmall)
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = AppColors.lightGrey,
                    modifier = Modifier.padding(AppSize.small, 0.dp, AppSize.small, AppSize.small)
                )
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "ID",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(AppSize.small, 0.dp, AppSize.small, AppSize.small)
                            .weight(1f)
                    )
                    Text(
                        text = comment.postId.toString(),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(AppSize.small, 0.dp, AppSize.small, AppSize.small)
                    )
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = AppColors.lightGrey,
                    modifier = Modifier.padding(AppSize.small, 0.dp, AppSize.small, AppSize.small)
                )
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(AppSize.small, 0.dp, AppSize.small, AppSize.small)
                            .weight(1f)
                    )
                    Text(
                        text = comment.email,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(AppSize.small, 0.dp, AppSize.small, AppSize.small)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailsPreview() {
    val comment = Comment(
        0,
        123,
        "odio adipisci rerum aut animi",
        "Nikita@garfield.biz",
        "quia molestiae reprehenderit quasi aspernatur\\naut expedita " +
                "occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus" +
                " saepe quia accusamus maiores nam est\\ncum et ducimus et vero " +
                "voluptates excepturi deleniti ratione"
    )
    DetailsView(comment, rememberNavController())
}
