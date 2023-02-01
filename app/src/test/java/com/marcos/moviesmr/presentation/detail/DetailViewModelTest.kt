package com.marcos.moviesmr.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcos.moviesmr.core.usecase.GetSimilarUseCase
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.RemoveFavoriteUseCase
import com.marcos.moviesmr.testing.MainCoroutineRule
import com.marcos.moviesmr.testing.model.MoviesFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getSimilarUseCase: GetSimilarUseCase

    @Mock
    private lateinit var checkFavoriteUseCase: CheckFavoriteUseCase

    @Mock
    private lateinit var addFavoriteUseCase: AddFavoriteUseCase

    @Mock
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase

    @Mock
    private lateinit var uiStateSimilarObserver: Observer<DetailViewModel.UiStateSimilar>

    @Mock
    private lateinit var uiStateFavoriteObserver: Observer<DetailViewModel.UiStateFavorite>

    private val movie = MoviesFactory().create(MoviesFactory.Popular.Deadpool)
    private val similar = listOf(MoviesFactory().create(MoviesFactory.Popular.Deadpool))

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(
            mainCoroutineRule.testDispatcherProvider,
            getSimilarUseCase,
            checkFavoriteUseCase,
            addFavoriteUseCase,
            removeFavoriteUseCase
        ).apply {
            stateSimilar.observeForever(uiStateSimilarObserver)
            stateFavorite.observeForever(uiStateFavoriteObserver)
        }
    }
}