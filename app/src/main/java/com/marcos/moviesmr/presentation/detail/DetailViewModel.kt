package com.marcos.moviesmr.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.GetSimilarUseCase
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.RemoveFavoriteUseCase
import com.marcos.moviesmr.presentation.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
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
        getSimilarUseCase.invoke(GetSimilarUseCase.GetSimilarParams(movieId))
            .watchStatus(
                loading = {
                    _uiState.value = UiState.Loading
                },
                success = { similar ->
                    _uiState.value = UiState.Success(similar)
                },
                error = {
                    _uiState.value = UiState.Error
                }
            )
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val similar: List<Movie>) : UiState()
        object Error : UiState()
        object Empty : UiState()
    }
}