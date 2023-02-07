package com.marcos.moviesmr.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.GetSearchUseCase
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.presentation.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val coroutinesDispatchers: CoroutinesDispatchers,
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    private val actionSearch = MutableLiveData<ActionSearch>()
    val stateSearch: LiveData<UiStateSearch> = actionSearch.switchMap {
        liveData(coroutinesDispatchers.main()) {
            when (it) {
                is ActionSearch.GetSearch -> {
                    getSearchUseCase.invoke(
                        GetSearchUseCase.GetSearchParams(it.query)
                    ).watchStatus(
                        success = { search ->
                            emit(UiStateSearch.Success(search))
                        },
                        error = {
                            emit(UiStateSearch.Empty)
                        }
                    )
                }
            }
        }
    }

    fun getSearch(query: String) {
        actionSearch.value = ActionSearch.GetSearch(query)
    }

    sealed class ActionSearch {
        data class GetSearch(val query: String) : ActionSearch()
    }

    sealed class UiStateSearch {
        data class Success(val search: List<Movie>) : UiStateSearch()
        object Empty : UiStateSearch()
    }
}