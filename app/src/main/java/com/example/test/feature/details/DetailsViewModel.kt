package com.example.test.feature.details

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.test.data.Comment
import com.example.test.data.repository.DetailsRepository
import com.example.test.viewmodel.BaseViewModel
import com.example.test.viewmodel.ViewModelStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    detailsRepository: DetailsRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    internal val uiState: ViewModelStateFlow<DetailsUiState> =
        viewModelStateFlow(DetailsUiState.Loading)

    private val commentId: Int = savedStateHandle["commentId"] ?: 0

    val comment: StateFlow<Comment?> =
        detailsRepository.fetchCommentDetails(
            id = commentId,
            onComplete = { uiState.tryEmit(key, DetailsUiState.Idle) },
            onError = { uiState.tryEmit(key, DetailsUiState.Error(it)) },
        ).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null,
            )
}

@Stable
internal sealed interface DetailsUiState {

    data object Idle : DetailsUiState

    data object Loading : DetailsUiState

    data class Error(val message: String?) : DetailsUiState
}
