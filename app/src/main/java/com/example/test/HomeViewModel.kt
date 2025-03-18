package com.example.test

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.example.test.data.Comment
import com.example.test.data.repository.HomeRepository
import com.example.test.viewmodel.BaseViewModel
import com.example.test.viewmodel.ViewModelStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : BaseViewModel() {

    internal val uiState: ViewModelStateFlow<HomeUiState> = viewModelStateFlow(HomeUiState.Loading)

    private val CommentFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val commentList: StateFlow<List<Comment>> = CommentFetchingIndex.flatMapLatest { page ->
        homeRepository.fetchCommentList(
            onStart = { uiState.tryEmit(key, HomeUiState.Loading) },
            onComplete = { uiState.tryEmit(key, HomeUiState.Idle) },
            onError = { uiState.tryEmit(key, HomeUiState.Error(it)) },
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

}

@Stable
internal sealed interface HomeUiState {

    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Error(val message: String?) : HomeUiState
}
