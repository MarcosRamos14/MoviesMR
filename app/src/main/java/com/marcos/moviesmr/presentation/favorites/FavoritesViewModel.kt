package com.marcos.moviesmr.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.core.usecase.favorite.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap { action ->
        liveData(coroutinesDispatchers.main()) {
            when (action) {
                is Action.GetAll -> {
                    getFavoriteUseCase.invoke().catch {
                        emit(UiState.ShowEmpty)
                    }.collect {
                        val items = it.map { movies ->
                            Movie(
                                movies.id,
                                movies.title,
                                movies.year,
                                movies.imageUrl,
                                movies.likes,
                                movies.popularity
                            )
                        }

                        val uiState = if (items.isEmpty()) {
                            UiState.ShowEmpty
                        } else UiState.ShowFavorite(items)
                        emit(uiState)
                    }
                }
            }
        }
    }

    fun getAll() {
        action.value = Action.GetAll
    }

    sealed class UiState {
        data class ShowFavorite(val favorites: List<Movie>) : UiState()
        object ShowEmpty : UiState()
    }

    sealed class Action {
        object GetAll : Action()
    }
}