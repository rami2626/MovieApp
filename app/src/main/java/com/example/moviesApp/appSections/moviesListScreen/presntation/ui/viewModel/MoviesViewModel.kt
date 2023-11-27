package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie
import com.example.moviesApp.appSections.moviesListScreen.domain.useCases.GetMovieUseCaseInterface
import com.example.moviesApp.util.DefaultPaginator
import com.example.moviesApp.networking.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCaseInterface
) : ViewModel() {
    private val _moviesSates = MutableStateFlow(ScreenState())
    val moviesState: StateFlow<ScreenState> = _moviesSates.asStateFlow()

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

    suspend fun  loadMovieDetails(movieId: Int) {

            getMovieUseCase.getMovieDetails(movieId).collect {
                when (it) {
                    is Resource.Success -> {
                        _movieDetailsSates.update { state ->
                            state.copy(id = state.id, title = state.title, isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        _movieDetailsSates.update { state ->
                            state.copy(error = state.error,isLoading = false)
                        }
                    }

                    is Resource.Loading -> {

                        _movieDetailsSates.update { state ->
                            state.copy(isLoading = true)
                        }
                    }

            }
        }
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<Movie> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)

data class DetailsScreenState(
    val isLoading: Boolean = true,
    val id: Int? = 0,
    val title: String? = "",
    val photoUrl: String? = "",
    val publishedAt: String? = "",
    val description: String? = "",
    val rate: Double? = 0.0,
    val error: String? = null,
)