package com.marcos.moviesmr.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.GetSimilarUseCase
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.core.usecase.base.ResultStatus
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSimilarUseCase: GetSimilarUseCase,
    checkFavoriteUseCase: CheckFavoriteUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    removeFavoriteUseCase: RemoveFavoriteUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val favorite = FavoriteUiActionStateLiveData(
        coroutinesDispatchers.main(),
        checkFavoriteUseCase,
        addFavoriteUseCase,
        removeFavoriteUseCase
    )

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getSimilar(movieId: Int) = viewModelScope.launch {
        getSimilarUseCase(GetSimilarUseCase.GetSimilarParams(movieId))
            .watchStatus()
    }

    private fun Flow<ResultStatus<List<Movie>>>.watchStatus() = viewModelScope.launch {
        collect { status ->
            _uiState.value = when (status) {
                ResultStatus.Loading -> UiState.Loading
                is ResultStatus.Success -> {
                    val similar = status.data

                    if (similar.isNotEmpty()) {
                        UiState.Success(similar)
                    } else UiState.Empty
                }
                is ResultStatus.Error -> UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val similar: List<Movie>) : UiState()
        object Error : UiState()
        object Empty : UiState()
    }
}