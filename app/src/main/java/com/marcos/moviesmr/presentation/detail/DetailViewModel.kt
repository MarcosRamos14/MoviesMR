package com.marcos.moviesmr.presentation.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.marcos.moviesmr.R
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.GetSimilarUseCase
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.RemoveFavoriteUseCase
import com.marcos.moviesmr.presentation.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val coroutinesDispatchers: CoroutinesDispatchers,
    private val getSimilarUseCase: GetSimilarUseCase,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
) : ViewModel() {

    @set:VisibleForTesting
    var currentFavoriteIcon = R.drawable.ic_favorite_unchecked

    private val actionSimilar = MutableLiveData<ActionSimilar>()
    val stateSimilar: LiveData<UiStateSimilar> = actionSimilar.switchMap {
        liveData(coroutinesDispatchers.main()) {
            when (it) {
                is ActionSimilar.GetSimilar -> {
                    getSimilarUseCase.invoke(
                        GetSimilarUseCase.GetSimilarParams(it.movieId)
                    ).watchStatus(
                        loading = {
                            emit(UiStateSimilar.Loading)
                        },
                        success = { similar ->
                            emit(UiStateSimilar.Success(similar))
                        },
                        error = {
                            emit(UiStateSimilar.Error)
                        }
                    )
                }
            }
        }
    }

    private val actionFavorite = MutableLiveData<ActionFavorite>()
    val state: LiveData<UiStateFavorite> = actionFavorite.switchMap {
        liveData(coroutinesDispatchers.main()) {
            when (it) {
                is ActionFavorite.CheckFavorite -> {
                    checkFavoriteUseCase.invoke(
                        CheckFavoriteUseCase.Params(it.movieId)
                    ).watchStatus(
                        success = { isFavorite ->
                            if (isFavorite) currentFavoriteIcon = R.drawable.ic_favorite_checked
                            emitFavoriteIcon()
                        }
                    )
                }
                is ActionFavorite.AddFavorite -> {
                    it.detailViewArgs.run {
                        addFavoriteUseCase.invoke(
                            AddFavoriteUseCase.Params(
                                movieId,
                                title,
                                year,
                                imageUrl,
                                likes,
                                popularity
                            )
                        ).watchStatus(
                            loading = {
                                emit(UiStateFavorite.Loading)
                            },
                            success = {
                                currentFavoriteIcon = R.drawable.ic_favorite_checked
                                emitFavoriteIcon()
                            },
                            error = {
                                emit(UiStateFavorite.Error(R.string.error_add_favorite))
                            }
                        )
                    }
                }
                is ActionFavorite.RemoveFavorite -> {
                    it.detailViewArgs.run {
                        removeFavoriteUseCase.invoke(
                            RemoveFavoriteUseCase.Params(
                                movieId,
                                title,
                                year,
                                imageUrl,
                                likes,
                                popularity
                            )
                        ).watchStatus(
                            loading = {
                                emit(UiStateFavorite.Loading)
                            },
                            success = {
                                currentFavoriteIcon = R.drawable.ic_favorite_unchecked
                                emitFavoriteIcon()
                            },
                            error = {
                                emit(UiStateFavorite.Error(R.string.error_remove_favorite))
                            }
                        )
                    }
                }
            }
        }
    }

    private suspend fun LiveDataScope<UiStateFavorite>.emitFavoriteIcon() {
        emit(UiStateFavorite.Icon(currentFavoriteIcon))
    }

    fun getSimilar(movieId: Int) {
        actionSimilar.value = ActionSimilar.GetSimilar(movieId)
    }

    fun checkFavorite(movieId: Int) {
        actionFavorite.value = ActionFavorite.CheckFavorite(movieId)
    }

    fun update(detailViewArgs: DetailViewArgs) {
        actionFavorite.value = if (currentFavoriteIcon == R.drawable.ic_favorite_unchecked) {
            ActionFavorite.AddFavorite(detailViewArgs)
        } else ActionFavorite.RemoveFavorite(detailViewArgs)
    }

    sealed class UiStateFavorite {
        object Loading : UiStateFavorite()
        data class Icon(@DrawableRes val icon: Int) : UiStateFavorite()
        data class Error(@StringRes val messageResId: Int) : UiStateFavorite()
    }

    sealed class ActionFavorite {
        data class CheckFavorite(val movieId: Int) : ActionFavorite()
        data class AddFavorite(val detailViewArgs: DetailViewArgs) : ActionFavorite()
        data class RemoveFavorite(val detailViewArgs: DetailViewArgs) : ActionFavorite()
    }

    sealed class ActionSimilar {
        data class GetSimilar(val movieId: Int) : ActionSimilar()
    }

    sealed class UiStateSimilar {
        object Loading : UiStateSimilar()
        data class Success(val similar: List<Movie>) : UiStateSimilar()
        object Error : UiStateSimilar()
        object Empty : UiStateSimilar()
    }
}