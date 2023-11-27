package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesApp.appSections.moviesListScreen.domain.useCases.GetMovieUseCaseInterface
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.detailsScreen.DetailsScreenState
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.HomeScreen.HomeScreenState
import com.example.moviesApp.util.DefaultPaginator
import com.example.moviesApp.networking.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCaseInterface
) : ViewModel() {
    private val _moviesSates = MutableStateFlow(HomeScreenState())
    val moviesState: StateFlow<HomeScreenState> = _moviesSates.asStateFlow()

    private val _movieDetailsSates = MutableStateFlow(DetailsScreenState())
    val movieDetailsSates: StateFlow<DetailsScreenState> = _movieDetailsSates.asStateFlow()

    private val paginator = DefaultPaginator(
        initialKey = _moviesSates.value.page,
        onLoadUpdated = { status ->
            _moviesSates.update {
                it.copy(
                    isLoading = status
                )
            }
        },
        onRequest = { nextPage ->
            getMovieUseCase.getMovies(nextPage)
        },
        getNextKey = {
            _moviesSates.update {
                it.copy(
                    page = it.page + 1
                )
            }
            _moviesSates.value.page
        },
        onError = { message ->
            _moviesSates.update {
                it.copy(
                    error = it.error
                )
            }
        },
        onSuccess = { items, newKey ->
            _moviesSates.update {
                it.copy(
                    items = _moviesSates.value.items + items,
                    page = newKey,
                    endReached = items.isEmpty()
                )
            }
        }
    )

    init {
        loadMoviesList()
    }

    fun loadMoviesList() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    suspend fun loadMovieDetails(movieId: Int) {
        _movieDetailsSates.update {
            it.copy(
                isLoading = true,
                error = null
            )
        }

        getMovieUseCase.getMovieDetails(movieId).catch {
            _movieDetailsSates.update {
                it.copy(
                    item = null,
                    isLoading = false,
                    error = it.error
                )
            }

        }.collect {
            when (it) {
                is Resource.Error -> {
                    _movieDetailsSates.update { state ->
                        state.copy(
                            item = null,
                            isLoading = false,
                            error = it.message
                        )
                    }
                }

                is Resource.Loading -> {
                    _movieDetailsSates.update { state ->
                        state.copy(isLoading = true, error = null, item = null)
                    }
                }

                is Resource.Success -> {
                    _movieDetailsSates.update { state ->
                        state.copy(item = it.data, isLoading = false, error = null)
                    }
                }
            }
        }
    }
}



